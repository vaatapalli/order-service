package com.orderservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.orderservice.entity.Item;
import com.orderservice.entity.Order;
import com.orderservice.entity.OrderItem;
import com.orderservice.exceptions.ItemNotFoundExcpetion;
import com.orderservice.exceptions.OrderNotFoundExcpetion;
import com.orderservice.exceptions.ServerNotFoundException;
import com.orderservice.proxy.OrderItemProxy;
import com.orderservice.repository.OrderServiceRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderServiceRepository serviceRepository;

    private final OrderItemProxy orderItemProxy;

    @Autowired
    public OrderServiceImpl(OrderServiceRepository serviceRepository, OrderItemProxy orderItemProxy) {
        this.serviceRepository = serviceRepository;
        this.orderItemProxy = orderItemProxy;
    }


    @Override
    @HystrixCommand(
            fallbackMethod = "findCachedOrderByCustomerName", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public List<Order> getOrders() {

        List<Order> orders = serviceRepository.findAll();
        List<OrderItem> orderItems = null;
        try {
            ResponseEntity<List<OrderItem>> response = orderItemProxy.getOrderItems();
            orderItems = response.getBody();
        } catch (FeignException e) {
            throw new ServerNotFoundException("Server not Found");
        }
        return this.addItemsToOrder(orders, orderItems);
    }

    public List<Order> addItemsToOrder(List<Order> orders, List<OrderItem> orderItems) {

        List<Order> result = new ArrayList<>();

        orders.stream().forEach(order -> {
            orderItems.stream().filter(orderItem ->
                    order.getCustomerName().equals(orderItem.getCustomerName())
            ).forEach(item -> {
                order.setOrder_items(item.getItems());
                result.add(order);
            });
        });

        return result;

    }


    public List<Order> findCachedOrderByCustomerName() {

        List<Item> items = new ArrayList<>();
        items.add(new Item(1L, "cache product 1", 10L));
        items.add(new Item(2L, "cache product 2", 20L));

        Order order = new Order();
        order.setCustomerName("cache customer");
        order.setShipping_address("cache Address");
        order.setTotal_cost(123.00);
        order.setOrder_items(items);

        List<Order> orders = new ArrayList<>();
        orders.add(order);

        return orders;
    }


    @Override
    public Optional<Order> getOrder(String customer_name) {
        Order order = serviceRepository.findByCustomerName(customer_name);
        if (order == null) {
            throw new OrderNotFoundExcpetion("No Order Found error");
        } else {
            String customerName = order.getCustomerName();
            List<Item> items = null;
            try {
                ResponseEntity<List<Item>> response = orderItemProxy.getItems(customerName);
                items = response.getBody();
            } catch (FeignException e) {
                HttpStatus httpStatus = HttpStatus.resolve(e.status());
                if (httpStatus == null) {
                    throw new ServerNotFoundException("Items-Server Not Found");
                }
                throw new ItemNotFoundExcpetion("No Item Found error");
            }
            order.setOrder_items(items);
            return Optional.of(order);
        }


    }


    @Override
    @Transactional(rollbackFor = {ServerNotFoundException.class})
    public Order addOrder(Order order) {
        List<Item> items = order.getOrder_items();
        OrderItem orderItem = new OrderItem(order.getCustomerName(), items);
        Order savedOrder = serviceRepository.save(order);

        try {
            orderItemProxy.addItems(orderItem);
        } catch (FeignException e) {
            HttpStatus httpStatus = HttpStatus.resolve(e.status());
            if (httpStatus == null) {
                throw new ServerNotFoundException("Item-Service is Not available");
            }
        }
        return savedOrder;
    }
}
