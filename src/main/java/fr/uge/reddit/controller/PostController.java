package fr.uge.reddit.controller;

import fr.uge.reddit.dto.PostDTO;
import fr.uge.reddit.entity.Message;
import fr.uge.reddit.entity.Post;
import fr.uge.reddit.repository.PostRepository;
import fr.uge.reddit.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PostController {
    @Autowired
    PostService postService;

    @ModelAttribute("post")
    public PostDTO credentials() {
        return new PostDTO();
    }

    @GetMapping("/post")
    public String getPost(Model model) {
        return "new-post";
    }

    @PostMapping("/post")
    public String postPost(@Valid @ModelAttribute("post") PostDTO post, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "new-post";
        }
        Post newPost = new Post();
        Message message = new Message();
        message.setBody(post.getBody());
        newPost.setTitle(post.getTitle());
        newPost.setMessage(message);
        postService.createNewPost(newPost);
        model.addAttribute("postService", postService);
        return "index";
    }
}
