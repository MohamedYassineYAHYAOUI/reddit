package fr.uge.reddit.services;

import fr.uge.reddit.entity.UserEntity;
import fr.uge.reddit.entity.VotesEntity;
import fr.uge.reddit.entity.VotesType;
import fr.uge.reddit.repository.MessageRepository;
import fr.uge.reddit.repository.UserRepository;
import fr.uge.reddit.repository.VotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class VoteServiceWithFailure {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

   @Transactional
    public void incrementVoteWithFailure(UserEntity currUser, Long idMessage, VotesEntity vote){
        var message = messageRepository.findById(idMessage).orElseThrow();
        if (vote  == null){
            var newVote = new VotesEntity(idMessage, VotesType.UPVOTE);
            currUser.getVotes().add(newVote);
            currUser.setVotes(currUser.getVotes());
            userRepository.save(currUser);
            message.inrementScore();
        }
        else{
            if (vote.getVote().equals(VotesType.DOWNVOTE)) {
                vote.setVote(VotesType.UPVOTE);
                message.setScore(message.getScore() + 2);
            }else {
                currUser.getVotes().remove(vote);
                message.setScore(message.getScore()  - 1);
            }
        }
        messageRepository.save(message);

    }
    @Transactional
    public void downVoteWithFailure(UserEntity currUser, Long idMessage, VotesEntity vote){
        var message = messageRepository.findById(idMessage).orElseThrow();
        if (vote  == null){
            var newVote = new VotesEntity(idMessage, VotesType.DOWNVOTE);
            currUser.getVotes().add(newVote);
            currUser.setVotes(currUser.getVotes());
            userRepository.save(currUser);
            message.setScore(message.getScore() - 1);
        }
        else{
            if (vote.getVote().equals(VotesType.UPVOTE)) {
                vote.setVote(VotesType.DOWNVOTE);
                message.setScore(message.getScore() - 2);
            }else{
                currUser.getVotes().remove(vote);
                message.setScore(message.getScore() + 1);
            }
        }
        messageRepository.save(message);
    }

}
