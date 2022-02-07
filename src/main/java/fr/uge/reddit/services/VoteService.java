package fr.uge.reddit.services;

import fr.uge.reddit.entity.MessageEntity;
import fr.uge.reddit.entity.UserEntity;
import fr.uge.reddit.entity.VotesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class VoteService {
    @Autowired
    private VoteServiceWithFailure voteServiceWithFailure;

    @Transactional
    public void incrementVote(UserEntity currUser, Long id, VotesEntity vote) {
        var retry = true;
        while (retry) {
            retry = false;
            try {
                voteServiceWithFailure.incrementVoteWithFailure(currUser, id, vote);
            } catch (org.springframework.orm.ObjectOptimisticLockingFailureException e) {
                retry = true;
            }
        }
    }

    @Transactional
    public void downVote(UserEntity currUser, Long id, VotesEntity vote) {
        var retry = true;
        while (retry) {
            retry = false;
            try {
                voteServiceWithFailure.downVoteWithFailure(currUser,id , vote);
            } catch (org.springframework.orm.ObjectOptimisticLockingFailureException e) {
                retry = true;
            }
        }
    }
}
