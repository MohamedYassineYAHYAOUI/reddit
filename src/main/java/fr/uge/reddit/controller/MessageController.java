package fr.uge.reddit.controller;

import fr.uge.reddit.dto.ReplyboxDTO;
import fr.uge.reddit.entity.MessageEntity;
import fr.uge.reddit.repository.MessageRepository;
import fr.uge.reddit.services.MessageService;
import fr.uge.reddit.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    @ModelAttribute("replybox")
    public ReplyboxDTO replyboxDTO() {
        return new ReplyboxDTO();
    }

    @PostMapping("/reply")
    public String getHomePage(@ModelAttribute("replybox") @Valid ReplyboxDTO replyboxDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            // TODO: Redirect to topic/id, or better: don't redirect at all!
            return "index";
        }

        messageService.reply(replyboxDTO);

        // TODO: Redirect to topic/id, or better: don't redirect at all!
        return "index";
    }
}
