package com.obsm.web.controller.employee;

import com.obsm.web.model.User;
import com.obsm.web.model.constant.UserRole;
import com.obsm.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class EmployeeController {
    private final UserService userService;

    public EmployeeController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User getUser(){
        return new User();
    }
    @GetMapping("/viewAllEmployees")
    public String showViewAllEmployeesPage(Model model){
        List<User> employees = userService.findAllByUserRole(UserRole.MODERATOR);

        model.addAttribute("employees",employees);

        return "viewAllEmployees";
    }

}
