package com.oservice.repository;

import com.oservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderServiceRepository extends JpaRepository<Order, String> {

    Order findByCustomerName(String customerName);
}
