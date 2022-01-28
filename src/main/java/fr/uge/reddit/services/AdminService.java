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
        System.out.println("---postId "+postId);
        var postEntity = topicRepository.findByIdWithReplies(postId);
        if(postEntity == null){
            return;
        }
        var replies = postEntity.getMessage().getReplies();
        //replies.forEach(r-> System.out.println(r.getScore() + " "+r.getAuthor()+ " "+r.getBody()));
        for (var r: replies) {
            System.out.println("replie "+r.getBody());
            for(var r1: r.getReplies()){
                System.out.println("replie2 "+r.getBody());            }
        }
        //replies.forEach(r->messageRepository.delete(r));

        //topicRepository.delete(postEntity);
    }

    
}
