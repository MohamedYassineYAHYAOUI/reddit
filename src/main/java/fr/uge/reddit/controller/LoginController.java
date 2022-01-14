package fr.uge.reddit.controller;

import fr.uge.reddit.dto.CredentialsDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("credentials", new CredentialsDTO());
        return "login";
    }
}
