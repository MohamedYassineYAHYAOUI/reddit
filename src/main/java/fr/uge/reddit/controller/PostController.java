package fr.uge.reddit.controller;

import fr.uge.reddit.dto.TopicDTO;
import fr.uge.reddit.entity.MessageEntity;
import fr.uge.reddit.entity.TopicEntity;
import fr.uge.reddit.services.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    TopicService postService;

    @ModelAttribute("post")
    public TopicDTO post() {
        return new TopicDTO();
    }

    @GetMapping("/createNewSubject")
    public String getPost(Model model) {return "new-post";}

    @GetMapping("/post/{id}")
    public String getPostId(@PathVariable("id") long id, Model model){
        var post = postService.getTopic(id).get();
        if(post != null){
            model.addAttribute("post", post);
            return "post";
        }
        return "index";
    }

    @PostMapping("/post")
    public String postPost(@Valid @ModelAttribute("post") TopicDTO post, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "new-post";
        }
        TopicEntity newPost = new TopicEntity();
        MessageEntity message = new MessageEntity();
        message.setBody(post.getBody());
        newPost.setTitle(post.getTitle());
        newPost.setMessage(message);
        postService.createNewTopic(newPost);
        model.addAttribute("postService", postService);
        return "index";
    }

}
