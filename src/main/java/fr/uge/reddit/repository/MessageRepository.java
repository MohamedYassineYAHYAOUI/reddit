package fr.uge.reddit.repository;

import fr.uge.reddit.entity.Message;
import fr.uge.reddit.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{


    Message findByAuthor(User author);

    @Query("SELECT e FROM MESSAGE e order by e.score ASC LIMIT :top")
    List<Message> listOfTopScores(@Param("top") int top);

    @Query("SELECT e FROM MESSAGE e order by e.score DESC LIMIT :worst")
    List<Message> listOfWorstScores(@Param("worst") int worst);


}
