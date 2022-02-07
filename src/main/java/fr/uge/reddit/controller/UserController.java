package fr.uge.reddit.controller;

import fr.uge.reddit.dto.ReplyboxDTO;
import fr.uge.reddit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model){
        var user = userService.getUser(id).orElse(null);
        var topics = userService.getTopicsByUser(id);
        if(user != null){
            model.addAttribute("user", user);
            model.addAttribute("topics", topics);
            model.addAttribute("replybox", new ReplyboxDTO());
            return "user";
        }
        return "redirect:/popular";
    }
}