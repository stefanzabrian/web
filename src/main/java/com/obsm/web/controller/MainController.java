package com.obsm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping({"/index", "/", "/home"})
    public String showIndexPage() {
        return "index";
    }

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

}
