package fr.uge.reddit.controller;

import fr.uge.reddit.services.AdminService;
import fr.uge.reddit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/delete/{id}")
    public String deleteMessage(@PathVariable("id") long messageId, Model model){
        adminService.updateDeleteMessage(messageId);
        return "redirect:/";
    }

}
