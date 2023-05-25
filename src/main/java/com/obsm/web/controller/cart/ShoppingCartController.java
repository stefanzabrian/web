package com.obsm.web.controller.cart;

import com.obsm.web.model.Product;
import com.obsm.web.service.ProductService;
import com.obsm.web.service.ProjectService;
import com.obsm.web.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
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
    @GetMapping("/shopping-cart-add/{id}")
    public String addProductToShoppingCart(
            @PathVariable("id") int id, @RequestParam String origin
    ) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            shoppingCartService.addProduct(productOptional.get());
        }
        if (origin != null && origin.equals("shopping-cart")) {
            return "redirect:/shopping-cart";
        }

        return "redirect:/shopping-cart-add?productAddedToCart";
    }

    @GetMapping("/shopping-cart-add-overview/{id}")
    public String addProductToShoppingCartFromOverview(
            @PathVariable("id") int id
            /*, @RequestParam String origin*/
    ) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            shoppingCartService.addProduct(productOptional.get());
        }
        /*if (origin != null && origin.equals("shopping-cart")) {
            return "redirect:/shopping-cart";
        }*/
        return "redirect:/portfolio-overview?productAddedToCart";
    }

    @GetMapping("/shopping-cart")
    public String showShoppingCartPage(Model model) {
        model.addAttribute("products", shoppingCartService.getAllProducts());
        model.addAttribute("totalPrice", shoppingCartService.totalPrice());
        model.addAttribute("deliveryStart", LocalDate.now().plusDays(2));
        model.addAttribute("deliveryEnd", LocalDate.now().plusDays(5));
        return "shopping-cart";
    }

    @GetMapping("/shopping-cart-remove/{id}")
    public String removeProductFromShoppingCart(@PathVariable("id") int id) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            shoppingCartService.removeProduct(productOptional.get());
        }
        return "redirect:/shopping-cart";
    }

    @GetMapping("/cart-checkout")
    public String cartCheckOut(Principal principal){
        shoppingCartService.checkOut(principal.getName());

        return "redirect:/index";
    }
}
