package fr.uge.reddit.repository;

import fr.uge.reddit.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Long>{

    //findbyauthor
    //findbyscor ordered
}
