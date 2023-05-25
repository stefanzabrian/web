package com.obsm.web.controller.admin;

import com.obsm.web.controller.dto.UserProfileDTO;
import com.obsm.web.model.User;
import com.obsm.web.model.constant.UserProfilePosition;
import com.obsm.web.model.constant.UserRole;
import com.obsm.web.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminUpdateController {
    private final UserService userService;

    public AdminUpdateController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute(name = "user")
    private UserProfileDTO userProfileDTO(){
        return new UserProfileDTO();
    }
    @ModelAttribute("roles")
    public UserRole[] getUserRoles(){
        return UserRole.values();
    }
    @ModelAttribute("positions")
    public UserProfilePosition[] getUserPositions(){
        return UserProfilePosition.values();
    }

    @GetMapping("/update-admin-profile/{email}")
    public String showUpdateAdminProfilePage(
            @PathVariable("email")
            String email,
            Model model
    ) {
        User ifUserIsPresent = userService.findByEmail(email)
                .orElseThrow();

        UserProfileDTO userProfileDTO = new UserProfileDTO(
                ifUserIsPresent.getId(),
                ifUserIsPresent.getEmail(),
                ifUserIsPresent.getUserProfile().getFistName(),
                ifUserIsPresent.getUserProfile().getLastName(),
                ifUserIsPresent.getUserProfile().getPosition(),
                ifUserIsPresent.getUserProfile().getSalary(),
                ifUserIsPresent.getUserProfile().getAddress(),
                ifUserIsPresent.getPhoneNumber()
                //ifUserIsPresent.getRole()
        );

        model.addAttribute("user", userProfileDTO);

        return "update-admin-profile";
    }

    @PostMapping("/update-admin-profile/{email}")
    public String updateAdminProfile(
            @PathVariable("email") String email,
            @Valid @ModelAttribute("user") UserProfileDTO userProfileDTO,
            BindingResult result
    ) {
        User currentUser = userService.findByEmail(email)
                .orElseThrow();

        if (result.hasErrors()) {
            return "update-admin-profile";
        }

        currentUser.getUserProfile().setFistName(userProfileDTO.getFirstName());
        currentUser.getUserProfile().setLastName(userProfileDTO.getLastName());
        currentUser.getUserProfile().setSalary(userProfileDTO.getSalary());
        currentUser.getUserProfile().setAddress(userProfileDTO.getAddress());
        currentUser.getUserProfile().setPosition(userProfileDTO.getPosition());

        currentUser.setEmail(userProfileDTO.getEmail());
        currentUser.setPhoneNumber(userProfileDTO.getPhoneNumber());
        //currentUser.setRole(userProfileDTO.getRole());

        userService.save(currentUser);

        return "redirect:/update-admin-profile/"+ email +"?success";
    }
}
