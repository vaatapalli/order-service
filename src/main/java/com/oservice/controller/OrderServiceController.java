package com.oservice.controller;

import com.oservice.entity.Order;
import com.oservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderServiceController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/get")
    public ResponseEntity<List<Order>> getItems() {

        List<Order> orders = orderService.getOrders();
        if (orders == null || orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(orders);


    }

    @RequestMapping(value = "/get/{customer_code}")
    public ResponseEntity<Order> findItem(@PathVariable String customer_code) {
        Optional<Order> order = orderService.findOrder(customer_code);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        }

        return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/createorder", method = RequestMethod.POST)
    public ResponseEntity<Order> setOrder(@Valid @RequestBody Order order) {

        Order order1 = orderService.setOrder(order);
        if (order1 == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(order1);

    }

}
