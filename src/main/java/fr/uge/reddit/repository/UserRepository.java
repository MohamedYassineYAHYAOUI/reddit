package fr.uge.reddit.repository;

import fr.uge.reddit.entity.TopicEntity;
import fr.uge.reddit.entity.UserEntity;
import fr.uge.reddit.entity.VotesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Objects;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);

}
