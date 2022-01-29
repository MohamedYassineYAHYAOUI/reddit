package fr.uge.reddit.controller;

import fr.uge.reddit.dto.TopicDTO;
import fr.uge.reddit.entity.MessageEntity;
import fr.uge.reddit.entity.TopicEntity;
import fr.uge.reddit.entity.TopicSortEnum;
import fr.uge.reddit.services.AdminService;
import org.springframework.data.domain.PageRequest;
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
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private AdminService adminService;

    @ModelAttribute("topic")
    public TopicDTO credentials() {
        return new TopicDTO();
    }

    @GetMapping("/create")
    public String getTopic(Model model) {return "new-topic";}

    @GetMapping("/{id}")
    public String getTopicById(@PathVariable("id") long id, Model model){
        var topic = topicService.getTopic(id).get();
        if(topic != null){
            model.addAttribute("topic", topic);
            return "topic";
        }
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTopic(@PathVariable("id") long topicId, Model model){
        adminService.deleteTopic(topicId);

        return "redirect:/";
    }

    @PostMapping("/create")
    public String createTopic(@Valid @ModelAttribute("subject") TopicDTO topic, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "new-topic";
        }
        TopicEntity newTopic = new TopicEntity();
        MessageEntity message = new MessageEntity();
        message.setBody(topic.getBody());
        message.setTimeStamp(Date.from(Instant.now()));
        newTopic.setTitle(topic.getTitle());
        newTopic.setMessage(message);
        topicService.createNewTopic(newTopic);

        return "redirect:/";
    }



}
