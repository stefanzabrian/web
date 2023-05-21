package com.obsm.web.controller.client;

import com.obsm.web.controller.dto.ClientProfileDTO;
import com.obsm.web.model.ClientProfile;
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

import java.util.Optional;

@Controller
public class ClientUpdateController {
    private final UserService userService;
    private final ClientProfileService clientProfileService;

    public ClientUpdateController(UserService userService, ClientProfileService clientProfileService) {
        this.userService = userService;
        this.clientProfileService = clientProfileService;
    }

    @ModelAttribute(name = "user")
    public ClientProfileDTO clientProfileDTO() {
        return new ClientProfileDTO();
    }

    @GetMapping("/update-client-profile/{id}")
    public String updateClientProfilePage(
            @PathVariable("id")
            int id,
            Model model
    ) {
        User ifUserIsPresent = userService.findById(id)
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

    @PostMapping("/update-client-profile/{id}")
    public String updateClientProfile(
            @PathVariable("id") int id,
            @Valid
            @ModelAttribute("user")
            ClientProfileDTO clientProfileDTO,
            BindingResult result
    ) {
        User currentUser = userService.findById(id).orElseThrow();
        clientProfileDTO.setId(currentUser.getId());

        if (result.hasErrors()) {
            return "update-client-profile";
        }

        Optional<ClientProfile> clientProfile = clientProfileService.getById(currentUser.getClientProfile().getId());
        if (clientProfile.isPresent()) {
            clientProfile.get().setFirstName(clientProfileDTO().getFirstName());
            clientProfile.get().setLastName(clientProfileDTO.getLastName());
            clientProfile.get().setAddress(clientProfileDTO.getAddress());

            clientProfileService.save(clientProfile.get());
        } else {
            ClientProfile clientProfileNew = clientProfileService.create(
                    clientProfileDTO.getFirstName(),
                    clientProfileDTO.getLastName(),
                    clientProfileDTO.getAddress());

            currentUser.setClientProfile(clientProfileNew);
        }


        currentUser.setPhoneNumber(clientProfileDTO.getPhoneNumber());

        userService.save(currentUser);

        return "redirect:/update-client-profile?success";
    }
}
