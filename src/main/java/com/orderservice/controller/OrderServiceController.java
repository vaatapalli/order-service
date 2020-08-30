package com.orderservice.controller;

import com.orderservice.entity.Order;
import com.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderServiceController {

    private final OrderService orderService;

    @Autowired
    public OrderServiceController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/{customerName}")
    public ResponseEntity<Order> getOrder(@PathVariable("customerName") String customerName) {
        Optional<Order> order = orderService.getOrder(customerName);
        return ResponseEntity.ok(order.get());
    }


    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<Order> addOrder(@Valid @RequestBody Order order) throws Exception {

        Order orderadd = orderService.addOrder(order);
        return ResponseEntity.ok(orderadd);

    }

}
