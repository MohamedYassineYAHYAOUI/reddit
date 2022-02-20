package fr.uge.reddit.services;

import fr.uge.reddit.dto.PageDTO;
import fr.uge.reddit.entity.TopicEntity;
import fr.uge.reddit.entity.TopicSortEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class TopicService {
    @Autowired
    private TopicServiceWithFailure topicServiceWithFailure;


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


    public Optional<TopicEntity> getTopic(long id){
        return topicServiceWithFailure.getTopicRepository().findById(id);
    }

    @Transactional
    public PageDTO<TopicEntity> findPaginated(TopicSortEnum sortType,int pageNumber, int pageSize){
        return PageDTO.fromPage(topicServiceWithFailure.getTopicsByOrder(sortType, pageNumber, pageSize));
    }

}
