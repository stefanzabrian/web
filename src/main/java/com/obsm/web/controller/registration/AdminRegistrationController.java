package com.obsm.web.controller.registration;

import com.obsm.web.controller.dto.UserDTO;
import com.obsm.web.model.ClientProfile;
import com.obsm.web.model.User;
import com.obsm.web.model.UserProfile;
import com.obsm.web.model.constant.ProductCategory;
import com.obsm.web.model.constant.UserRole;
import com.obsm.web.service.ClientProfileService;
import com.obsm.web.service.UserProfileService;
import com.obsm.web.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/register-admin")
public class AdminRegistrationController {
    private final UserProfileService userProfileService;
    private final ClientProfileService clientProfileService;
    private final UserService userService;

    public AdminRegistrationController(UserProfileService userProfileService, ClientProfileService clientProfileService, UserService userService) {
        this.userProfileService = userProfileService;
        this.clientProfileService = clientProfileService;
        this.userService = userService;
    }

    @ModelAttribute(name = "user")
    public UserDTO userDTO() {
        return new UserDTO();
    }

    @GetMapping()
    public String showAdminRegisterPage() {
        return "register-admin";
    }

    @ModelAttribute("roles")
    public UserRole[] getUserRole() {
        return UserRole.values();
    }

    @ModelAttribute("categories")
    public ProductCategory[] getProductCategory() {
        return ProductCategory.values();
    }

    @PostMapping
    public String registerAdminAccount(
            @ModelAttribute("user")
            @Valid
            UserDTO userDTO,
            BindingResult result
    ) {
        Optional<User> user = userService.findByEmail(userDTO.getEmail());
        if (user.isPresent()) {
            result.rejectValue("email", null, "Email already exists");
        }
        if (result.hasErrors()) {
            return "register-admin";
        }

        ClientProfile clientProfile = new ClientProfile();
        UserProfile userProfile = new UserProfile();
        userService.createAdmin(
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getPhoneNumber(),
                userDTO.getRole(),
                clientProfileService.save(clientProfile),
                userProfileService.save(userProfile)
        );

        return "redirect:/register-admin?success";

    }
}
