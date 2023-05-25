package com.obsm.web.service;

import com.obsm.web.model.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final Map<Product, Integer> cart = new LinkedHashMap<>();

    @Override
    public void addProduct(Product product) {
        if (cart.containsKey(product)) {
            cart.put(product, cart.get(product) + 1);
        } else {
            cart.put(product, 1);
        }
    }

    @Override
    public void removeProduct(Product product) {
        if (cart.containsKey(product)) {
            if (cart.get(product) == 1) {
                cart.remove(product);
            } else {
                cart.put(product, cart.get(product) - 1);
            }
        }
    }

    @Override
    public void clearProducts() {
        cart.clear();

    }

    @Override
    public double totalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            totalPrice += entry.getValue() * entry.getKey().getPrice();
        }
        return totalPrice;
    }

    @Override
    public void checkOut(String userEmail) {
        //System.out.println(userEmail);
        //TODO: find user by email
        // get user profile
        // create new order and save it
        // create new product order objects and asign order to the user profile, and save them in DB
        // send email to user
        cart.clear();

    }

    @Override
    public Map<Product, Integer> getAllProducts() {
        return Collections.unmodifiableMap(cart);
    }


}
