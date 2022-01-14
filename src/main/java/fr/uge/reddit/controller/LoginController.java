package fr.uge.reddit.controller;

import fr.uge.reddit.dto.CredentialsDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {
    @ModelAttribute("credentials")
    public CredentialsDTO credentials() {
        return new CredentialsDTO();
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login";
    }
}
