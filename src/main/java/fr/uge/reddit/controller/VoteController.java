package fr.uge.reddit.controller;

import fr.uge.reddit.dto.TopicDTO;

import fr.uge.reddit.entity.UserEntity;

import fr.uge.reddit.entity.VotesEntity;
import fr.uge.reddit.repository.UserRepository;
import fr.uge.reddit.repository.VotesRepository;
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

    @ModelAttribute("topic")
    public TopicDTO credentials() {
        return new TopicDTO();
    }


    @Autowired
    private VotesRepository votesRepository;

    @PostMapping("/upVote/{id}")
    public String upvoteTopic(@PathVariable("id") long messageId, Model model){
        System.out.println(" ----> "+ messageId);
        UserEntity currUser = userService.currentUser();
        VotesEntity vote = votesRepository.findbyMessageId(messageId);
        /*VotesEntity votes = new VotesEntity(topicId, VotesType.UPVOTE);

        currUser.getVotes().add(votes);
        currUser.setVotes(currUser.getVotes());
        userRepository.save(currUser);*/
        voteService.incrementVote(currUser, messageId, vote);
        return "redirect:/";
    }

    @PostMapping("/downVote/{id}")
   public String downVoteTopic(@PathVariable("id") long messageId, Model model){
        System.out.println("=>    " + messageId);
        UserEntity currUser = userService.currentUser();
        VotesEntity vote = votesRepository.findbyMessageId(messageId);
        voteService.downVote(currUser, messageId, vote);
        return "redirect:/";
    }
}
