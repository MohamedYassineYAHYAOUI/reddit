package fr.uge.reddit.controller;

import fr.uge.reddit.dto.TopicDTO;
import fr.uge.reddit.entity.MessageEntity;
import fr.uge.reddit.entity.TopicEntity;
import fr.uge.reddit.entity.UserEntity;
import fr.uge.reddit.services.AdminService;
import fr.uge.reddit.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import fr.uge.reddit.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private AdminService adminService;

    @ModelAttribute("topic")
    public TopicDTO topicDTO() {
        return new TopicDTO();
    }

    @GetMapping("/create")
    public String getTopic(Model model) {return "new-topic";}

    @GetMapping("/{id}")
    public String getTopicById(@PathVariable("id") long id, Model model){
        var topic = topicService.getTopic(id).orElse(null);
        if(topic != null){
            model.addAttribute("topic", topic);
            return "topic";
        }
        return "redirect:/popular";
    }

    @PostMapping("/delete/{id}")
    public String deleteTopic(@PathVariable("id") long topicId, Model model){
        adminService.deleteTopic(topicId);

        return "redirect:/popular";
    }

    @PostMapping("/create")
    public String createTopic(@Valid @ModelAttribute("topic") TopicDTO topic, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "new-topic";
        }
        // var user = UserService.currentUser();
        var newTopic = new TopicEntity();
        var message = new MessageEntity();
        message.setBody(topic.getBody());
        // message.setAuthor(user);
        message.setTimeStamp(new Date());
        newTopic.setTitle(topic.getTitle());
        newTopic.setMessage(message);
        topicService.createNewTopic(newTopic);
        model.addAttribute("topicService", topicService);
        return "redirect:/popular";
    }

    /*
    @GetMapping("/topics")
    public String topics(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                         @RequestParam(value = "size", required = false, defaultValue = "5")int size,
                         Model model){
        var topics = topicService.findPaginated(PageRequest.of(pageNumber-1 , size));

    }*/

}
