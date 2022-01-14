package fr.uge.reddit.repository;

import fr.uge.reddit.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

}
