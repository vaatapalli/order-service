package com.oservice.entity;

import javax.persistence.*;

@Embeddable
public class Item {

    @Column(name="_product_code")
    Long product_code;

    @Column(name="_product_name")
    String product_name;

    @Column(name="_quantity")
    Long quantity;

    public Item() {
    }

    public Item(Long product_code, String product_name, Long quantity) {
        this.product_code = product_code;
        this.product_name = product_name;
        this.quantity = quantity;
    }

    public Long getProduct_code() {
        return product_code;
    }

    public void setProduct_code(long product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
