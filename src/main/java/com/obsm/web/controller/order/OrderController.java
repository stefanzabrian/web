package com.obsm.web.controller.order;

import com.obsm.web.model.ClientProfile;
import com.obsm.web.model.Order;
import com.obsm.web.model.User;
import com.obsm.web.model.constant.OrderStatus;
import com.obsm.web.service.OrderService;
import com.obsm.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/my-client-orders")
    public String showMyOrdersPage(Model model, Principal principal) {
        model.addAttribute("orders", orderService.findAllByUserEmail(principal.getName()));
        return "my-client-orders";
    }

    @GetMapping("/view-all-orders")
    public String showOrdersPage(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "view-all-orders";
    }

    @GetMapping("/client-view-order/{id}")
    public String showViewOrderPage(
            @PathVariable("id") int orderId,
            Model model,
            Principal principal
    ) {

        User user = userService.findByEmail(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        ClientProfile clientProfile = user.getClientProfile();
        if (clientProfile == null) {
            throw new IllegalArgumentException("Client Profile does not exist");
        }
        Optional<Order> optionalOrder = orderService.findByIdAndClientProfile(orderId, clientProfile);
        if (optionalOrder.isEmpty()) {
            throw new IllegalArgumentException("Order not found");
        }

        model.addAttribute("order", optionalOrder.get());
        model.addAttribute("clientProfile", clientProfile);
        return "client-view-order";
    }

    @GetMapping("/admin-view-order/{id}")
    public String showAdminViewOrderPage(
            @PathVariable("id") int orderId,
            Model model
    ) {

        Optional<Order> optionalOrder = orderService.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new IllegalArgumentException("Order not found");
        }

        model.addAttribute("order", optionalOrder.get());
        return "admin-view-order";
    }

    @GetMapping("/admin-delete-order/{id}")
    public String deleteOrder(@PathVariable("id") int orderId) {
        Optional<Order> optionalOrder = orderService.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new IllegalArgumentException("Order not found");
        }
        orderService.delete(optionalOrder.get());
        return "redirect:/view-all-orders";
    }

    @ModelAttribute("statuses")
    public OrderStatus[] getOrderStatuses() {
        return OrderStatus.values();
    }

    @GetMapping("/update-order/{id}")
    public String showUpdateOrderPage(@PathVariable("id") int id,
                                      Model model) {
        Optional<Order> optionalOrder = orderService.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new IllegalArgumentException("Order not found");
        }
        model.addAttribute("order", optionalOrder.get());
        return "update-order";
    }

    @PostMapping("/update-order/{id}")
    public String updateOrder(@PathVariable("id") int id, Order order) {
        Optional<Order> optionalOrder = orderService.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new IllegalArgumentException("Order not found");
        }
        optionalOrder.get().setStatus(order.getStatus());
        orderService.update(optionalOrder.get());
        return "redirect:/view-all-orders";
    }
}
