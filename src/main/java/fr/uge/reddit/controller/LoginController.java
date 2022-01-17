package fr.uge.reddit.controller;

import fr.uge.reddit.dto.CredentialsDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController{



    @ModelAttribute("credentials")
    public CredentialsDTO credentials() {
        return new CredentialsDTO();
    }

    @GetMapping("login")
    public String getLogin(Model model) {
        return "login";
    }


    @PostMapping("login")
    public String checkLoginInfo(@Valid @ModelAttribute("credentials") CredentialsDTO  credential,
                                BindingResult bindingResult, Model model) {
        System.out.println("test");
        if(bindingResult.hasErrors()) {
            //gestion error
            return "login";
        }

        //gestion login
        return "login";
    }


}
