package fr.uge.reddit.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RedirectController {

    @GetMapping("/redirect")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public String getRedirect(@RequestParam("to") String to, Model model) {
        model.addAttribute("to", to);
        return "redirect";
    }
}
