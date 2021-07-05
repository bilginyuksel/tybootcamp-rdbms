package com.tybootcamp.ecomm.entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Double totalPrice;

    @OneToMany
    private Map<Long, ShoppingCartItem> items = new HashMap<>();

    public void add(ShoppingCartItem item) {
        items.computeIfPresent(item.getId(), (id, shoppingCartItem) -> {
            shoppingCartItem.incrementQuantity();
            return shoppingCartItem;
        });
        items.putIfAbsent(item.getId(), item);
    }

    public void remove(long id) {
        if (!items.containsKey(id)) {
            throw new RuntimeException();
        }
        ShoppingCartItem itemToRemove = items.get(id);
        itemToRemove.decrementQuantity();

        if (itemToRemove.getQuantity() <= 0) {
            items.remove(itemToRemove.getId());
        }
    }

    public void removeAll(long id) {
        items.remove(id);
    }

    public double calculatePrice() {
        double price = 0.0;
        for (Map.Entry<Long, ShoppingCartItem> itemEntry : items.entrySet()) {
            ShoppingCartItem item = itemEntry.getValue();

            price += item.getCost();
        }

        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setItems(Map<Long, ShoppingCartItem> items) {
        this.items = items;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Map<Long, ShoppingCartItem> getItems() {
        return items;
    }

    public long getId() {
        return id;
    }
}
