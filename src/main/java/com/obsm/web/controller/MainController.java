package com.obsm.web.controller;

import com.obsm.web.model.Product;
import com.obsm.web.model.User;
import com.obsm.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }
    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    private void getGetCurrentUser(Model model , Principal principal){
        String email = principal.getName();
        User user = userService.getByEmail(email);

        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        model.addAttribute("user",user);
    }
    // client maps
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping({"/index", "/", "/home"})
    public String showIndexPage(Model model, Principal principal) {
        getGetCurrentUser(model,principal);
        return "index";
    }

    @GetMapping("/about")
    public String showAboutPage(Model model ,Principal principal) {
        getGetCurrentUser(model, principal);
        return "about";
    }

    @GetMapping("/contact")
    public String showContactPage(Model model, Principal principal) {
        getGetCurrentUser(model, principal);
        return "contact";
    }

    @GetMapping("/pricing")
    public String showPricingPage(Model model, Principal principal) {
        getGetCurrentUser(model, principal);
        return "pricing";
    }

    @GetMapping("/faq")
    public String showFaqPage(Model model, Principal principal) {
        getGetCurrentUser(model, principal);
        return "faq";
    }

    @GetMapping("/blog-home")
    public String showBlogHomePage(Model model, Principal principal) {
        getGetCurrentUser(model, principal);
        return "blog-home";
    }

    @GetMapping("/blog-post")
    public String showBlogPostPage(Model model, Principal principal) {
        getGetCurrentUser(model, principal);
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
        return "update-admin-profile";
    }
    // portal maps
    @GetMapping("/portal")
    public String showPortalPage(){
        return "portal";
    }


}
