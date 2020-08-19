package com.oservice.service;

import com.oservice.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> getOrders();
    Optional<Order> findOrder(String customer_name);
    Order setOrder(Order order);
}
