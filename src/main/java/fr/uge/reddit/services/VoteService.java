package fr.uge.reddit.services;

import fr.uge.reddit.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class VoteService {
    @Autowired
    private VoteServiceWithFailure voteServiceWithFailure;

    @Transactional
    public void incrementVote(Long id) {
        var retry = true;
        while (retry) {
            retry = false;
            try {
                voteServiceWithFailure.incrementVoteWithFailure(id);
            } catch (org.springframework.orm.ObjectOptimisticLockingFailureException e) {
                retry = true;
            }
        }
    }

    @Transactional
    public void downVote(Long id) {
        var retry = true;
        while (retry) {
            retry = false;
            try {
                voteServiceWithFailure.downVoteWithFailure(id);
            } catch (org.springframework.orm.ObjectOptimisticLockingFailureException e) {
                retry = true;
            }
        }
    }
}
