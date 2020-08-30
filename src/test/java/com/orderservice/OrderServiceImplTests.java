package com.orderservice;

import com.orderservice.entity.Item;
import com.orderservice.entity.Order;
import com.orderservice.entity.OrderItem;
import com.orderservice.proxy.OrderItemProxy;
import com.orderservice.repository.OrderServiceRepository;
import com.orderservice.service.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


class OrderServiceImplTests {

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderServiceRepository serviceRepository;

    @Mock
    OrderItemProxy orderItemProxy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getOrders() {
        List<Order> orders = new ArrayList<>();

        Item item = new Item();
        item.setProduct_code(123);
        item.setProduct_name("product");
        item.setQuantity(1);

        List<Item> items = new ArrayList<>();
        items.add(item);

        Order order = new Order();
        order.setCustomerName("customer name");
        order.setShipping_address("address");
        order.setTotal_cost(200.00);
        //order.setOrder_items(items);

        orders.add(order);

        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setCustomerName("customer name");
        orderItem.setItems(items);

        orderItems.add(orderItem);

        ResponseEntity<List<OrderItem>> response = ResponseEntity.of(Optional.of(orderItems));

        Mockito.when(serviceRepository.findAll()).thenReturn(orders);
        Mockito.when(orderItemProxy.getOrderItems()).thenReturn(response);
        List<Order> result = orderService.getOrders();
        Order resultOrder = result.get(0);

        Assertions.assertEquals("customer name", resultOrder.getCustomerName());
        Assertions.assertEquals("address", resultOrder.getShipping_address());
        Assertions.assertEquals(200.00, resultOrder.getTotal_cost());
        Assertions.assertEquals(items, resultOrder.getOrder_items());

    }

    @Test
    void getOrder() {
        Order order = new Order();
        order.setCustomerName("customer name");
        order.setShipping_address("address");
        order.setTotal_cost(200.00);

        Item item = new Item();
        item.setProduct_code(123);
        item.setProduct_name("product");
        item.setQuantity(1);

        List<Item> items = new ArrayList<>();
        items.add(item);

        ResponseEntity<List<Item>> response = ResponseEntity.of(Optional.of(items));

        Mockito.when(serviceRepository.findByCustomerName(Mockito.anyString())).thenReturn(order);
        Mockito.when(orderItemProxy.getItems(Mockito.anyString())).thenReturn(response);

        Order resultOrder = orderService.getOrder("customer name").get();

        Assertions.assertEquals("customer name", resultOrder.getCustomerName());
        Assertions.assertEquals("address", resultOrder.getShipping_address());
        Assertions.assertEquals(200.00, resultOrder.getTotal_cost());
        Assertions.assertEquals(items, resultOrder.getOrder_items());

    }

    @Test
    void addOrder() {
        Item item = new Item();
        item.setProduct_code(123);
        item.setProduct_name("product");
        item.setQuantity(1);

        List<Item> items = new ArrayList<>();
        items.add(item);

        Order order = new Order();
        order.setCustomerName("customer name");
        order.setShipping_address("address");
        order.setTotal_cost(200.00);
        order.setOrder_items(items);

        ResponseEntity<List<Item>> response = ResponseEntity.of(Optional.of(items));

        Mockito.when(serviceRepository.save(Mockito.any(Order.class))).thenReturn(order);
        Mockito.when(orderItemProxy.addItems(Mockito.any(OrderItem.class))).thenReturn(response);

        Order resultOrder = orderService.addOrder(order);
        Assertions.assertEquals("customer name", resultOrder.getCustomerName());
        Assertions.assertEquals("address", resultOrder.getShipping_address());
        Assertions.assertEquals(200.00, resultOrder.getTotal_cost());
        Assertions.assertEquals(items, resultOrder.getOrder_items());

    }

    @Test
    void findCachedOrderByCustomerName() {
        Order order = new Order();
        List<Item> items = new ArrayList<>();
        order.setCustomerName("cache customer");
        items.add(new Item(1L, "cache product 1", 10L));
        items.add(new Item(2L, "cache product 2", 20L));
        order.setShipping_address("cache Address");
        order.setTotal_cost(123.00);
        order.setOrder_items(items);
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        List<Order> orderList = orderService.findCachedOrderByCustomerName();
        Order resultOrder = orderList.get(0);

        Assertions.assertEquals("cache customer", resultOrder.getCustomerName());
        Assertions.assertEquals("cache Address", resultOrder.getShipping_address());
        Assertions.assertEquals(123.00, resultOrder.getTotal_cost());


    }
}
