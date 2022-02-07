package fr.uge.reddit.services;

import fr.uge.reddit.entity.TopicEntity;
import fr.uge.reddit.entity.TopicSortEnum;
import fr.uge.reddit.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TopicServiceWithFailure {
    @Autowired
    TopicRepository topicRepository;

    @Transactional
    public void createNewTopic(TopicEntity topic){
        topicRepository.save(topic);
    }

    public Page<TopicEntity> getTopicsByOrder(TopicSortEnum sortType, int pageNumber, int pageSize){
        PageRequest request = PageRequest.of(pageNumber,pageSize);
        switch (sortType){
            case NEWEST:
                 return topicRepository.findAllOrderedByNewest(request);
            case OLDEST:
                return topicRepository.findAllOrderedByOldest(request);
            case BEST:
                return topicRepository.findAllOrderedByBest(request);
            case WORST:
                return topicRepository.findAllOrderedByWorst(request);
            default:
                throw new IllegalArgumentException("sort methode not supported");
        }
    }

    public TopicRepository getTopicRepository() {
        return topicRepository;
    }
}
