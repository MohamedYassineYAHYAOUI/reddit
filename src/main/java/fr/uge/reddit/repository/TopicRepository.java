package fr.uge.reddit.repository;

import fr.uge.reddit.entity.TopicEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<TopicEntity, Long> {


    @Query("SELECT p FROM TopicEntity p JOIN FETCH p.message m JOIN FETCH m.replies WHERE p.id = :postId")
    public TopicEntity findByIdWithReplies(@Param("postId") Long postId);

    //public List<>
}
