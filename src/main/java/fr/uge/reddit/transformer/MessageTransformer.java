package fr.uge.reddit.transformer;

import fr.uge.reddit.dto.MessageDTO;
import fr.uge.reddit.entity.MessageEntity;

import java.util.Collection;
import java.util.List;

public class MessageTransformer implements Transformer<MessageDTO, MessageEntity> {

    @Override
    public MessageDTO modelToDto(MessageEntity model) {
        return null;
    }


    @Override
    public List<MessageDTO> modelToDto(Collection<MessageEntity> model) {
        return null;
    }
}
