package com.obsm.web.service;

import com.obsm.web.model.*;
import com.obsm.web.model.constant.OrderStatus;
import com.obsm.web.repository.OrderRepository;
import com.obsm.web.repository.ProductOrderRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.mail.MessagingException;
import java.util.*;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final String ORDER_PREFIX = "ORDER-";
    private final Map<Product, Integer> cart = new LinkedHashMap<>();
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ProductOrderRepository productOrderRepository;
    private final MailService mailService;

    public ShoppingCartServiceImpl(UserService userService, OrderRepository orderRepository, ProductOrderRepository productOrderRepository, MailService mailService) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.productOrderRepository = productOrderRepository;
        this.mailService = mailService;
    }

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
        User user = userService.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        ClientProfile clientProfile = user.getClientProfile();
        if (clientProfile == null) {
            throw new IllegalArgumentException("Client Profile does not exist");
        }

        Order order = new Order();
        order.setOrderNumber(ORDER_PREFIX + new Random().nextInt(1_000_000));
        order.setDateOfOrder(new Date());
        order.setStatus(OrderStatus.CREATED);
        order.setTotalAmount(totalPrice());
        order.setClientProfile(clientProfile);
        orderRepository.save(order);

        for (Map.Entry<Product, Integer> entry : getAllProducts().entrySet()){
            ProductOrder productOrder = new ProductOrder();
            productOrder.setOrder(order);
            productOrder.setProduct(entry.getKey());
            productOrder.setQuantity(entry.getValue());
            productOrder.setProductPrice(entry.getKey().getPrice() * entry.getValue());
            productOrderRepository.save(productOrder);
        }

        try {
            mailService.sendEmail(
                    "office@marta.company",
                    userEmail,
                    "New Order",
                    "New order with total amount: " + totalPrice()
            );
        } catch (MessagingException e) {
            e.printStackTrace();
        }

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
