package fr.uge.reddit.services;

import fr.uge.reddit.entity.TopicEntity;
import fr.uge.reddit.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TopicServiceWithFailure {
    @Autowired
    TopicRepository topicRepository;

    @Transactional
    public void createNewTopic(TopicEntity topic){
        topicRepository.save(topic);
    }

    public TopicRepository getTopicRepository() {
        return topicRepository;
    }
}
