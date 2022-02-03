package fr.uge.reddit.repository;

import fr.uge.reddit.entity.MessageEntity;
import fr.uge.reddit.entity.TopicEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends CrudRepository<TopicEntity, Long> {


    @Query("SELECT p FROM TopicEntity p JOIN FETCH p.message m JOIN FETCH m.replies WHERE p.id = :postId")
    public TopicEntity findByIdWithReplies(@Param("postId") Long postId);

    @Query("SELECT p FROM TopicEntity p WHERE p.message.author.id= :userId")
    List<TopicEntity> findTopicByUserId(@Param("userId") Long postId);
    //public List<>
}
