package fr.uge.reddit.controller;

import fr.uge.reddit.dto.PostDTO;
import fr.uge.reddit.entity.MessageEntity;
import fr.uge.reddit.entity.PostEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import fr.uge.reddit.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/subject")
public class PostController {
    @Autowired
    PostService postService;

    @ModelAttribute("post")
    public PostDTO credentials() {
        return new PostDTO();
    }

    @GetMapping("/createNewSubject")
    public String getPost(Model model) {return "new-post";}

    @GetMapping("/post/{id}")
    public String getPostId(@PathVariable("id") long id, Model model){
        var post = postService.getPost(id).get();
        if(post != null){
            model.addAttribute("post", post);
            return "post";
        }
        return "index";
    }

    @PostMapping("/post")
    public String postPost(@Valid @ModelAttribute("post") PostDTO post, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "new-post";
        }
        PostEntity newPost = new PostEntity();
        MessageEntity message = new MessageEntity();
        message.setBody(post.getBody());
        newPost.setTitle(post.getTitle());
        newPost.setMessage(message);
        postService.createNewPost(newPost);
        model.addAttribute("postService", postService);
        return "index";
    }

}
