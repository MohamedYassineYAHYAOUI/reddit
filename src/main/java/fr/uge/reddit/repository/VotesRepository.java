package fr.uge.reddit.repository;


import fr.uge.reddit.entity.UserEntity;
import fr.uge.reddit.entity.VotesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface VotesRepository extends CrudRepository<UserEntity, Long> {

    @Query ("SELECT p FROM VotesEntity p WHERE  p.messageId = :messageId")
    public VotesEntity findbyMessageId(@Param("messageId") Long messageId);
}
