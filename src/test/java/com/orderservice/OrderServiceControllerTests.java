package com.orderservice;

import com.orderservice.controller.OrderServiceController;
import com.orderservice.entity.Item;
import com.orderservice.entity.Order;
import com.orderservice.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


class OrderServiceControllerTests {

    @InjectMocks
    OrderServiceController orderServiceController;

    @Mock
    OrderService orderService;

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
        order.setOrder_items(items);

        orders.add(order);

        Mockito.when(orderService.getOrders()).thenReturn(orders);
        ResponseEntity<List<Order>> responseEntity = orderServiceController.getOrders();

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(orders, responseEntity.getBody());
    }

    @Test
    void getOrder() {
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

        Mockito.when(orderService.getOrder(Mockito.anyString())).thenReturn(Optional.of(order));
        ResponseEntity<Order> responseEntity = orderServiceController.getOrder("customer name");

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(order, responseEntity.getBody());

    }

    @Test
    void addOrder() throws Exception {
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

        Mockito.when(orderService.addOrder(Mockito.any(Order.class))).thenReturn(order);
        ResponseEntity<Order> responseEntity = orderServiceController.addOrder(order);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(order, responseEntity.getBody());


    }

}
