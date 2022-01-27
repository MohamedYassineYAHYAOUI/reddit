package fr.uge.reddit.services;

import fr.uge.reddit.entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TopicService {
    @Autowired
    TopicServiceWithFailure topicServiceWithFailure;


    @Transactional
    public void createNewTopic(TopicEntity topic){
        var retry=true;
        while(retry) {
            retry=false;
            try {
                topicServiceWithFailure.createNewTopicWrong(topic); //!!!!!!!!!!
            } catch (org.springframework.orm.ObjectOptimisticLockingFailureException e){
                retry=true;
            }
        }
    }

    public List<TopicEntity> getAllTopics(){
        var topics = StreamSupport
                .stream(topicServiceWithFailure.getTopicRepository().findAll().spliterator(), false)
                .collect(Collectors.toList());
        return topics;
    }

    public Optional<TopicEntity> getTopic(long id){
        var topic = topicServiceWithFailure.getTopicRepository().findById(id);
        return topic;
    }
}
