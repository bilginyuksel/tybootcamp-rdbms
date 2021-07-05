package com.tybootcamp.ecomm.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends Profile {
    @NotNull
    private String name;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private ShoppingCart shoppingCart;

    public Customer() {
        shoppingCart = new ShoppingCart();
    }

    public void addItem(ShoppingCartItem item) {
        shoppingCart.add(item);
    }

    public void removeOneItem(long itemId) {
        shoppingCart.remove(itemId);
    }

    public void removeAllItems(long itemId) {
        shoppingCart.removeAll(itemId);
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
