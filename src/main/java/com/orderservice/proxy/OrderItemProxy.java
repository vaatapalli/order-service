package com.orderservice.proxy;

import com.orderservice.entity.Item;
import com.orderservice.entity.OrderItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "order-item", url = "http://localhost:8090", decode404 = false)
public interface OrderItemProxy {

    @GetMapping(value = "/items/{customerName}")
    ResponseEntity<List<Item>> getItems(@PathVariable("customerName") String customerName);

    @GetMapping(value = "/items")
    ResponseEntity<List<OrderItem>> getOrderItems();

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    ResponseEntity<List<Item>> addItems(@RequestBody OrderItem orderItem);

}

