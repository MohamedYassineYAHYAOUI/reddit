package fr.uge.reddit.repository;

import fr.uge.reddit.entity.TopicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends PagingAndSortingRepository<TopicEntity, Long> {


    @Query("SELECT t FROM TopicEntity t LEFT JOIN FETCH t.message m WHERE t.id = :topicEntity")
    TopicEntity findByIdWithReplies(@Param("topicEntity") Long topicEntity);

    @Query("SELECT t FROM TopicEntity t JOIN t.message m ORDER BY m.timeStamp ASC")
    Page<TopicEntity> findAllOrderedByOldest(Pageable request);

    @Query("SELECT t FROM TopicEntity t JOIN t.message m ORDER BY m.timeStamp DESC")
    Page<TopicEntity> findAllOrderedByNewest(Pageable request);

    @Query("SELECT t FROM TopicEntity t JOIN t.message m ORDER BY m.score ASC")
    Page<TopicEntity> findAllOrderedByWorst(Pageable request);

    @Query("SELECT t FROM TopicEntity t JOIN t.message m ORDER BY m.score DESC")
    Page<TopicEntity> findAllOrderedByBest(Pageable request);

    @Query("SELECT t FROM TopicEntity t LEFT JOIN t.message.replies r where t.id = :id")
    TopicEntity findTopicById(@Param("id") Long id);

    @Query("SELECT p FROM TopicEntity p WHERE p.message.author.id= :userId")
    Page<TopicEntity> findTopicByUserId(@Param("userId") Long postId, Pageable request);
}
