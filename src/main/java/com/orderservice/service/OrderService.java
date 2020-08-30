package com.orderservice.service;

import com.orderservice.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> getOrders();

    Optional<Order> getOrder(String customer_name);

    Order addOrder(Order order) throws Exception;
}
