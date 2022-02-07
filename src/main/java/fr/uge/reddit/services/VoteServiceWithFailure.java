package fr.uge.reddit.services;

import fr.uge.reddit.entity.MessageEntity;
import fr.uge.reddit.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Service
public class VoteServiceWithFailure {

    @Autowired
    MessageRepository messageRepository;

    @PersistenceUnit
    EntityManagerFactory emf;

    @PersistenceContext
    EntityManager em;

    @Transactional
        public void incrementVoteWithFailure(Long idmessage ){
        var message = messageRepository.findById(idmessage).orElseThrow();
        System.out.println(message.getId());
        //message.setScore(message.getScore() + 1);
        message.inrementScore();
        messageRepository.save(message);
    }
    @Transactional
    public void downVoteWithFailure(Long idmessage ){
        var message = messageRepository.findById(idmessage).orElseThrow();
        System.out.println(message.getId());
        message.setScore(message.getScore() - 1);
        messageRepository.save(message);
    }

}
