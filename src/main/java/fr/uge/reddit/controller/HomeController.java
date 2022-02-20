package fr.uge.reddit.controller;

import fr.uge.reddit.dto.CredentialsDTO;
import fr.uge.reddit.dto.PageDTO;
import fr.uge.reddit.entity.TopicSortEnum;
import fr.uge.reddit.services.TopicService;
import fr.uge.reddit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/{sortType}","/"}, method = RequestMethod.GET)
    public String home_page(@PathVariable(value = "sortType" ) Optional<TopicSortEnum> sort,
                            @RequestParam(value = "page",defaultValue = "0") int page,
                            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                            Model model) {
        var topics = topicService.findPaginated(sort.orElse(TopicSortEnum.NEWEST),page , pageSize);
        model.addAttribute("topicsPage", topics);

        model.addAttribute("sortMethod",sort==null?"Newest":sort.orElse(TopicSortEnum.NEWEST).getSortType());

        var list =  Arrays.stream(TopicSortEnum.values()).map(TopicSortEnum::getSortType).toArray(String[]::new);

        model.addAttribute("allFilters",list);


        model.addAttribute("userUpVote", userService.getUpVote());
        model.addAttribute("userDownVote", userService.getDownVote());

        return "home_page";


    }
}
