package com.oservice.service;

import com.oservice.entity.Order;
import com.oservice.repository.OrderServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderServiceRepository serviceRepository;

    @Override
    public List<Order> getOrders() {
        return serviceRepository.findAll();
    }

    @Override
    public Optional<Order> findOrder(String customer_name) {
        Order order=serviceRepository.findByCustomerName(customer_name);
        return Optional.of(order);
    }

    @Override
    public Order setOrder(Order order) {
        return serviceRepository.save(order);
    }
}
