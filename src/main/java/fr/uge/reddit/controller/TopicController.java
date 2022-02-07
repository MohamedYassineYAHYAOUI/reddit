package fr.uge.reddit.controller;

import fr.uge.reddit.dto.ReplyboxDTO;
import fr.uge.reddit.dto.TopicDTO;
import fr.uge.reddit.entity.MessageEntity;
import fr.uge.reddit.entity.TopicEntity;
import fr.uge.reddit.services.AdminService;
import fr.uge.reddit.services.MessageService;
import fr.uge.reddit.services.TopicService;
import fr.uge.reddit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;


@Controller
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private MessageService messageService;

    @ModelAttribute("replybox")
    public ReplyboxDTO replyboxDTO() {
        return new ReplyboxDTO();
    }

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
            System.out.println("tpopic foind ---");
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
    public String createTopic(@Valid @ModelAttribute("topic") TopicDTO topic, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "new-topic";
        }
        // var user = UserService.currentUser();
        var newTopic = new TopicEntity();
        var message = new MessageEntity();
        message.setBody(topic.getBody());
        message.setAuthor(userService.currentUser());

        message.setTimeStamp(new Date());
        newTopic.setTitle(topic.getTitle());
        newTopic.setMessage(message);
        topicService.createNewTopic(newTopic);
        //model.addAttribute("topicService", topicService);
        return "redirect:/";
    }



    @PostMapping("/{id}")
    public String getHomePage(@PathVariable("id") long id, @ModelAttribute("replybox") @Valid ReplyboxDTO replyboxDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "redirect:/topic/" + id;
        }

        // TODO: If possible, avoid redirecting to a dynamic url

        messageService.reply(replyboxDTO);

        return "redirect:/topic/" + id;
    }

}
