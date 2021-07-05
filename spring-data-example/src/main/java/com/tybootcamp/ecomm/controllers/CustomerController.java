package com.tybootcamp.ecomm.controllers;

import com.tybootcamp.ecomm.entities.Customer;
import com.tybootcamp.ecomm.entities.ShoppingCart;
import com.tybootcamp.ecomm.entities.ShoppingCartItem;
import com.tybootcamp.ecomm.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> addNewCustomer(@RequestBody @Valid Customer customer) {
        return ResponseEntity.ok(customerService.addNewCustomer(customer));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody @Valid Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(customer));
    }

    @GetMapping("/{customerId}/cart")
    public ResponseEntity<ShoppingCart> getCart(@PathVariable long customerId) {
        return ResponseEntity.ok(customerService.getCustomerCart(customerId));
    }

    @PostMapping("/{customerId}/cart")
    public ResponseEntity<ShoppingCartItem> addItemToCart(@PathVariable long customerId,
                                                          @RequestBody ShoppingCartItem item) {
        return ResponseEntity.ok(customerService.addItemToCart(customerId, item));
    }

    @DeleteMapping("/{customerId}/cart/{itemId}")
    public ResponseEntity<ShoppingCartItem> removeAllItemsFromCart(@PathVariable long customerId,
                                                                   @PathVariable long itemId) {
        customerService.removeAllItemsFromCart(customerId, itemId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{customerId}/cart/{itemId}")
    public ResponseEntity<ShoppingCartItem> removeItemFromCart(@PathVariable long customerId,
                                                               @PathVariable long itemId) {
        customerService.removeItemFromCart(customerId, itemId);
        return ResponseEntity.ok().build();
    }
}
