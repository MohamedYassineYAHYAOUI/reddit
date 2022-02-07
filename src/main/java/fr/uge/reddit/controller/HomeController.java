package fr.uge.reddit.controller;

import fr.uge.reddit.dto.CredentialsDTO;
import fr.uge.reddit.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private TopicService topicService;




    @GetMapping("/popular")
    public String getHomePage(Model model) {
      var topicsModel = topicService.getAllTopics();
      //model.addAttribute();
        model.addAttribute("topicService", topicService);
      return "home_page";
    }

    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/popular";
    }


}
