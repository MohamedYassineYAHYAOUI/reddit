package fr.uge.reddit.transformer;

import fr.uge.reddit.dto.TopicDTO;
import fr.uge.reddit.entity.MessageEntity;
import fr.uge.reddit.entity.TopicEntity;
import fr.uge.reddit.services.TopicService;
import fr.uge.reddit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class TopicTransformer implements Transformer<TopicDTO, TopicEntity> {

    @Autowired
    private TopicService topicService;

    @Autowired
    private MessageTransformer messageTransformer;

    /*
    @Override
    public TopicEntity dtoToModel(TopicDTO dto) {
        if(dto==null){
            return null;
        }
        var model = new TopicEntity();
        model.setTitle(dto.getTitle());
        var msgEntity = new MessageEntity();
        msgEntity.setBody(dto.getBody());
        msgEntity.setScore(dto.getScore());

        msgEntity.setAuthor();
        msgEntity.setTimeStamp(dto.getTimeStamp());
        var replies = messageTransformer.dtoToModel(dto.getReplies());
        msgEntity.setReplies(replies);
        return model;
    }*/


    //TODO: a vérifier que les get ne font pas des select en plus
    @Override
    public TopicDTO modelToDto(TopicEntity model) {
        if(model == null){
            return null;
        }
        var dto = new TopicDTO();
        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setBody(model.getMessage().getBody());
        dto.setScore(model.getMessage().getScore());
        dto.setAuthor(model.getMessage().getAuthor().getLogin());
        dto.setTimeStamp(model.getMessage().getTimeStamp());
        dto.setReplies(messageTransformer.modelToDto(model.getMessage().getReplies()));
        return dto;
    }
    /*
    @Override
    public List<TopicEntity> dtoToModel(Collection<TopicDTO> dtoList) {
        if( dtoList == null){
            return null;
        }
        var modelList = new ArrayList<TopicEntity>();
        for (TopicDTO topic: dtoList) {
            modelList.add(this.dtoToModel(topic));
        }
        return modelList;
    }
*/
    @Override
    public List<TopicDTO> modelToDto(Collection<TopicEntity> modelList) {
        if( modelList == null){
            return null;
        }
        var dtoList = new ArrayList<TopicDTO>();
        for (TopicEntity topicModel: modelList) {
            dtoList.add(this.modelToDto(topicModel));
        }
        return dtoList;
    }
}
