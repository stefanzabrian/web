package com.obsm.web.controller;

import com.obsm.web.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    // client maps
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping({"/index", "/", "/home"})
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }

    @GetMapping("/pricing")
    public String showPricingPage() {
        return "pricing";
    }

    @GetMapping("/faq")
    public String showFaqPage() {
        return "faq";
    }

    @GetMapping("/blog-home")
    public String showBlogHomePage() {
        return "blog-home";
    }

    @GetMapping("/blog-post")
    public String showBlogPostPage() {
        return "blog-post";
    }

    @GetMapping("/portfolio-item")
    public String showPortfolioItemPage(Product product) {
        return "portfolio-item";
    }
    @GetMapping("/updateProduct")
    public String showUpdateProductPage(Product product){
        return "updateProduct";
    }
    // user maps
    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgot-password";
    }

    @GetMapping("/update-client-profile")
    public String showClientProfileUpdatePage() {
        return "update-client-profile";
    }

    @GetMapping("/update-admin-profile")
    public String showAdminProfileUpdatePage() {
        return "update-client-profile";
    }
    // portal maps
    @GetMapping("/portal")
    public String showPortalPage(){
        return "portal";
    }


}
