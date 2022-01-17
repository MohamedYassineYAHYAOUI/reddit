package fr.uge.reddit.controller;

import fr.uge.reddit.dto.PostDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PostController {
    @ModelAttribute("post")
    public PostDTO credentials() {
        return new PostDTO();
    }

    @GetMapping("/post")
    public String getPost(Model model) {return "new-post";}

    @PostMapping("/post")
    public String postPost(@Valid @ModelAttribute("post") PostDTO post, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "new-post";
        }
        return "home_page";
    }
}
