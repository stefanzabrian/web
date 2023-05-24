package com.obsm.web.controller.client;

import com.obsm.web.controller.dto.ClientProfileDTO;
import com.obsm.web.model.User;
import com.obsm.web.service.ClientProfileService;
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
public class ClientUpdateController {
    private final UserService userService;

    public ClientUpdateController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute(name = "user")
    public ClientProfileDTO clientProfileDTO() {
        return new ClientProfileDTO();
    }

    @GetMapping("/update-client-profile/{email}")
    public String showUpdateClientProfilePage(
            @PathVariable("email")
            String email,
            Model model
    ) {
        User ifUserIsPresent = userService.findByEmail(email)
                .orElseThrow();
        // aici trebuie adaugat profilul clientului

        ClientProfileDTO clientProfileDTO = new ClientProfileDTO(
                ifUserIsPresent.getId(),
                ifUserIsPresent.getClientProfile().getFirstName(),
                ifUserIsPresent.getClientProfile().getLastName(),
                ifUserIsPresent.getClientProfile().getAddress(),
                ifUserIsPresent.getPhoneNumber()
        );
        model.addAttribute("user", clientProfileDTO);

        return "update-client-profile";
    }

    @PostMapping("/update-client-profile/{email}")
    public String updateClientProfile(
            @PathVariable("email") String email,
            @Valid
            @ModelAttribute("user")
            ClientProfileDTO clientProfileDTO,
            BindingResult result
    ) {
        User currentUser = userService.findByEmail(email).orElseThrow();


        if (result.hasErrors()) {
            return "update-client-profile";
        }


        currentUser.getClientProfile().setFirstName(clientProfileDTO.getFirstName());
        currentUser.getClientProfile().setLastName(clientProfileDTO.getLastName());
        currentUser.getClientProfile().setAddress(clientProfileDTO.getAddress());
        currentUser.setPhoneNumber(clientProfileDTO.getPhoneNumber());

        userService.save(currentUser);

        return "redirect:/update-client-profile/"+ email +"?success";
    }
}
