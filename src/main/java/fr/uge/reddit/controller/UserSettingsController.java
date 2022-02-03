package fr.uge.reddit.controller;

import fr.uge.reddit.dto.CredentialsDTO;
import fr.uge.reddit.entity.UserEntity;
import fr.uge.reddit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserSettingsController {

    @Autowired
    private UserService userService;

    @GetMapping("/settings")
    public String changePasswordPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CredentialsDTO credentials = new CredentialsDTO();
        credentials.setLogin(authentication.getName());
        model.addAttribute("credentials", credentials);
        return "config_password";
    }

    @PostMapping("/settings")
    public String changePassword(@Valid @ModelAttribute("credentials") CredentialsDTO credentials,
                                 //@Value("oldPassword") String oldPassword,
                                 BindingResult bindingResult,
                                 Model model){
        model.addAttribute("credentials",credentials);
        //Check de l'ancien mdp non fonctionnel
        if(bindingResult.hasErrors() /*|| !userService.checkUserOldPasswordMatch(credentials.getLogin(), oldPassword)*/){
            return "redirect:/all";
        }
        try {
            userService.updatePassword(credentials.getLogin(), credentials.getPassword());
        }catch(IllegalArgumentException e){
            model.addAttribute("err_msg", e.getMessage());
            return "config_password";
        }
        return "config_password";
    }
}
