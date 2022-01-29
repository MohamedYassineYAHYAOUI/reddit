package fr.uge.reddit.services;

import fr.uge.reddit.dto.TopicDTO;
import fr.uge.reddit.entity.TopicEntity;
import fr.uge.reddit.entity.TopicSortEnum;
import fr.uge.reddit.transformer.TopicTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class TopicService {
    @Autowired
    private TopicServiceWithFailure topicServiceWithFailure;

    @Autowired
    private TopicTransformer topicTransformer;


    @Transactional
    public void createNewTopic(TopicEntity topic){
        var retry=true;
        while(retry) {
            retry=false;
            try {
                topicServiceWithFailure.createNewTopic(topic);
            } catch (org.springframework.orm.ObjectOptimisticLockingFailureException e){
                retry=true;
            }
        }
    }

    public List<TopicDTO> getAllTopics(){
        var topics = StreamSupport
                .stream(topicServiceWithFailure.getTopicRepository().findAll().spliterator(), false)
                .collect(Collectors.toList());
        return topicTransformer.modelToDto(topics);
    }

    public Optional<TopicEntity> getTopic(long id){
        var topic = topicServiceWithFailure.getTopicRepository().findById(id);
        return topic;
    }

    @Transactional
    public Page<TopicEntity> findPaginated(TopicSortEnum sortType,int pageNumber, int pageSize){
        Page<TopicEntity> listOfTopics = topicServiceWithFailure.getTopicsByOrder(sortType, pageNumber, pageSize);
        return listOfTopics;
    }

}
