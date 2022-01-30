package fr.uge.reddit.controller;

import fr.uge.reddit.entity.UserEntity;
import fr.uge.reddit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/password")
    public String changePasswordPage(Model model){
        model.addAttribute("userAttribute", new UserEntity()); //A remplacer par l'utilisateur actuellement connecté sur la session
        return "config_password";
    }

    @PostMapping("/password")
    public String changePassword(@Valid @ModelAttribute("userAttribute") UserEntity user,
                                 BindingResult bindingResult,
                                 Model model){
        if(bindingResult.hasErrors()){
            return "redirect:/all";
        }
        return "config_password";
    }
}
