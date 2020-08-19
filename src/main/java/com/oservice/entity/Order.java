package com.oservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Set;


@Entity(name = "orderservice")
public class Order {

    @Id
    @NotNull(message = "customerName cannot be null")
    String customerName;

    @Column(name = "_orderDate")
    Date orderDate;

    @Column(name = "_shipping_address")
    @NotNull(message = "shipping_address cannot be null")
    String shipping_address;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_items_id"))
    @Column(name = "oitems")
    @NotNull(message = "order_items cannot be null")
    Set<Item> order_items;

    @Column(name = "_total_cost")
    @NotNull(message = "total_cost cannot be null")
    Double total_cost;

    public Order() {

        this.orderDate = new Date(System.currentTimeMillis());
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Set<Item> getOrder_items() {
        return order_items;
    }

    public void setOrder_items(Set<Item> order_items) {
        this.order_items = order_items;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }


    public Double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(Double total_cost) {
        this.total_cost = total_cost;
    }
}
