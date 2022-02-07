package fr.uge.reddit.services;

import fr.uge.reddit.dto.ReplyboxDTO;
import fr.uge.reddit.entity.MessageEntity;
import fr.uge.reddit.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Objects;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    @Transactional
    @Retryable
    public void reply(ReplyboxDTO replyboxDTO) {
        Objects.requireNonNull(replyboxDTO);

        var reply = new MessageEntity();
        reply.setBody(replyboxDTO.getBody());
        reply.setTimeStamp(new Date());
        reply.setAuthor(userService.currentUser());
        messageRepository.save(reply);

        var to = messageRepository.findById(replyboxDTO.getTo()).orElseThrow();
        to.getReplies().add(reply);
        messageRepository.save(to);
    }
}
