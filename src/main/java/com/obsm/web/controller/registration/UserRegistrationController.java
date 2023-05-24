package com.obsm.web.controller.registration;

import com.obsm.web.controller.dto.UserRegistrationDTO;
import com.obsm.web.model.ClientProfile;
import com.obsm.web.model.User;
import com.obsm.web.model.constant.UserRole;
import com.obsm.web.service.ClientProfileService;
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
@RequestMapping("/register")
public class UserRegistrationController {
    private final ClientProfileService clientProfileService;
    private final UserService userService;

    public UserRegistrationController(ClientProfileService clientProfileService, UserService userService) {
        this.clientProfileService = clientProfileService;
        this.userService = userService;
    }

    @ModelAttribute(name = "user")
    public UserRegistrationDTO userRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @GetMapping
    public String showUserRegistrationPage() {
        return "register";
    }

    @PostMapping
    public String registerUserAccount(
            @ModelAttribute("user")
            @Valid
            UserRegistrationDTO userRegistrationDTO,
            BindingResult result) {

        Optional<User> user = userService.findByEmail(userRegistrationDTO.getEmail());
        if (user.isPresent()) {
            result.rejectValue("email", null, "Email already exists");
        }
        if (result.hasErrors()) {
            return "register";
        }

        userService.create(
                userRegistrationDTO.getEmail(),
                userRegistrationDTO.getPassword(),
                userRegistrationDTO.getPhoneNumber(),
                UserRole.CLIENT,
                clientProfileService.save(new ClientProfile())

        );

        return "redirect:/register?success";
    }
}