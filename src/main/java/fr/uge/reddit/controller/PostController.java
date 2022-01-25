package fr.uge.reddit.controller;

import fr.uge.reddit.dto.PostDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/subject")
public class PostController {
    @ModelAttribute("post")
    public PostDTO credentials() {
        return new PostDTO();
    }

    @GetMapping("/createNewSubject")
    public String getPost(Model model) {return "new-post";}

    @PostMapping("/createNewSubject")
    public String postPost(@Valid @ModelAttribute("post") PostDTO post, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "new-post";
        }
        return "index";
    }

}
