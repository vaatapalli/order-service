package com.orderservice.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
public class Item {

    @Column(name = "_product_code")
    @NotNull(message = "product_code cannot be null")
    @DecimalMin(value="1",message = "product_code must be greater than or equal to 1")
    private Long product_code;

    @Column(name = "_product_name")
    @NotNull(message = "product_name cannot be null")
    @NotBlank(message = "product_name is mandatory")
    private String product_name;

    @Column(name = "_quantity")
    @NotNull(message = "quantity cannot be null")
    @DecimalMin(value="1",message = "quantity must be greater than or equal to 1")
    private Long quantity;

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
