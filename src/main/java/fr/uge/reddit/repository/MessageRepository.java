package fr.uge.reddit.repository;

import fr.uge.reddit.entity.MessageEntity;
import fr.uge.reddit.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Long>{


    MessageEntity findByAuthor(UserEntity author);

    @Query("SELECT e FROM MessageEntity e order by e.score ASC")
    List<MessageEntity> listOfTopScores(Pageable pageable);

    @Query("SELECT e FROM MessageEntity e order by e.score DESC")
    List<MessageEntity> listOfWorstScores(Pageable pageable);


}
