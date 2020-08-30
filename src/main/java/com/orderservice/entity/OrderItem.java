package com.orderservice.entity;

import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderItem {

    @NotNull(message = "customerName cannot be null")
    private String customerName;

    @NotNull(message = "items cannot be null")
    private List<Item> items;

    public OrderItem() {
    }

    public OrderItem(String customerName, List<Item> items) {
        this.customerName = customerName;
        this.items = items;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
