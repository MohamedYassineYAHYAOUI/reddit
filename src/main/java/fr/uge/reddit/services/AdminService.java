package fr.uge.reddit.services;


import fr.uge.reddit.repository.MessageRepository;
import fr.uge.reddit.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class AdminService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Transactional
    public void deleteTopic(long postId){
        Objects.requireNonNull(postId);

        var topicOpt = topicRepository.findTopicById(postId);
        if(topicOpt == null){
            throw new IllegalArgumentException("Post doesn't exist");
        }
        var topicMsg = topicOpt.getMessage();
        var replies = topicMsg.getReplies();
        for(var msg: replies){
            messageRepository.delete(msg);
        }
        messageRepository.delete(topicMsg);
        topicRepository.delete(topicOpt);
    }


    
}
