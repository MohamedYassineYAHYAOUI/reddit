package fr.uge.reddit.controller;

import fr.uge.reddit.dto.TopicDTO;

import fr.uge.reddit.entity.UserEntity;
import fr.uge.reddit.entity.VotesEntity;
import fr.uge.reddit.entity.VotesType;
import fr.uge.reddit.repository.UserRepository;
import fr.uge.reddit.services.UserService;
import fr.uge.reddit.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("topic")
    public TopicDTO credentials() {
        return new TopicDTO();
    }


    @PostMapping("/upvote/{id}")
    public String upvoteTopic(@PathVariable("id") long topicId, Model model){
        System.out.println(topicId);
        /*UserEntity currUser = userService.currentUser();
        VotesEntity votes = new VotesEntity(topicId, VotesType.UPVOTE);

        currUser.getVotes().add(votes);
        currUser.setVotes(currUser.getVotes());
        userRepository.save(currUser);*/
        voteService.incrementVote(topicId);
        return "redirect:/popular";
    }

    @PostMapping("/downVote/{id}")
   public String downVoteTopic(@PathVariable("id") long topicId, Model model){
        System.out.println(topicId);
        voteService.downVote(topicId);
        return "redirect:/popular";
    }
}
