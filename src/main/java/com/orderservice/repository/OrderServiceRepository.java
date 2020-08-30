package com.orderservice.repository;

import com.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderServiceRepository extends JpaRepository<Order, String> {
    Order findByCustomerName(String customerName);
}
