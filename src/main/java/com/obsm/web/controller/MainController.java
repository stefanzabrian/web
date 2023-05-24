package com.obsm.web.controller;

import com.obsm.web.model.Product;
import com.obsm.web.model.Project;
import com.obsm.web.model.Task;
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

    @GetMapping("/template")
    public String showTemplatePage() {
        return "template";
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    private void setCurrentUserModel(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.getByEmail(email);

        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("user", user);
    }

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
    public String showUpdateProductPage(Product product) {
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
    public String showPortalPage() {
        return "portal";
    }

    @GetMapping("/viewProjectById")
    public String showViewProjectById(Project project) {
        return "viewProjectById";
    }


}
