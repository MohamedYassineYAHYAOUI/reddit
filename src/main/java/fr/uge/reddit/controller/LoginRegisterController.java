package fr.uge.reddit.controller;

import fr.uge.reddit.dto.CredentialsDTO;
import fr.uge.reddit.entity.UserEntity;
import fr.uge.reddit.entity.UserRoles;
import fr.uge.reddit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class LoginRegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("credentials", new CredentialsDTO());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("already_logged_in", !(authentication instanceof AnonymousAuthenticationToken));
        return "login";
    }

    @GetMapping("/register")
    public String createUserPage(Model model){
        model.addAttribute("credentials", new CredentialsDTO());
        model.addAttribute("registered_successfully", false);
        return "register_user_form";
    }

    @PostMapping("/register")
    public String createUser(@Valid @ModelAttribute("credentials") CredentialsDTO credentials,
                             BindingResult bindingResult, Model model){
        model.addAttribute("credentials",credentials );
        model.addAttribute("registered_successfully", false);
        if(bindingResult.hasErrors()){
            return "register_user_form";
        }
        try{
            userService.createNewUserAccount(new UserEntity(credentials.getLogin(), credentials.getPassword(), UserRoles.USER));
        }catch(IllegalArgumentException e){
            model.addAttribute("err_msg", e.getMessage());
            return "register_user_form";
        }
        //TODO : des notifications de type "compte cr??e"
        model.addAttribute("registered_successfully", true);
        return "register_user_form";
    }

}
