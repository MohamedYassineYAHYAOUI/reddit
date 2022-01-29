package fr.uge.reddit.transformer;

import fr.uge.reddit.dto.MessageDTO;
import fr.uge.reddit.entity.MessageEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class MessageTransformer implements Transformer<MessageDTO, MessageEntity> {

    @Override
    public MessageDTO modelToDto(MessageEntity model) {
        if(model ==null){
            return null;
        }
        var dto = new MessageDTO();
        dto.setBody(model.getBody());
        dto.setScore(model.getScore());
        dto.setAuthor(model.getAuthor().getLogin());
        dto.setTimeStamp(model.getTimeStamp());
        dto.setReplies(new ArrayList<>());
        for(MessageEntity msgEntity: model.getReplies()){
            dto.getReplies().add(modelToDto(msgEntity));
        }
        return dto;
    }


    @Override
    public List<MessageDTO> modelToDto(Collection<MessageEntity> modelList) {
        if( modelList == null){
            return null;
        }
        var dtoList = new ArrayList<MessageDTO>();
        for (MessageEntity topicModel: modelList) {
            dtoList.add(this.modelToDto(topicModel));
        }
        return dtoList;
    }
}
