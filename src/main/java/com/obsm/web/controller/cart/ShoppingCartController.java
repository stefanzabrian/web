package com.obsm.web.controller.cart;

import com.obsm.web.model.Product;
import com.obsm.web.service.ProductService;
import com.obsm.web.service.ProjectService;
import com.obsm.web.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/shopping-cart-add-item/{id}")
    public String addProductToShoppingCartFromItem(
            @PathVariable("id") int id
    ) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            shoppingCartService.addProduct(productOptional.get());
        }
        return "redirect:/portfolio-item/{id}?productAddedToCart";
    }
    @GetMapping("/shopping-cart-add-overview/{id}")
    public String addProductToShoppingCartFromOverview(
            @PathVariable("id") int id
    ) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            shoppingCartService.addProduct(productOptional.get());
        }
        return "redirect:/portfolio-overview?productAddedToCart";
    }

    @GetMapping("/shopping-cart")
    public String showShoppingCartPage(Model model) {
        model.addAttribute("products", shoppingCartService.getAllProducts());
        model.addAttribute("totalPrice", shoppingCartService.totalPrice());
        return "shopping-cart";
    }
}
