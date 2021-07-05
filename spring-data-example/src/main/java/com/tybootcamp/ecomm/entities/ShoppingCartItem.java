package com.tybootcamp.ecomm.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ShoppingCartItem {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToOne
    private Product product;

    private int quantity;

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCost() {
        return product.getPrice() * quantity;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
