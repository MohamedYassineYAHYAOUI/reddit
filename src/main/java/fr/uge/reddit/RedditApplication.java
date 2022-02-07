package fr.uge.reddit;


import fr.uge.reddit.entity.MessageEntity;
import fr.uge.reddit.entity.TopicEntity;
import fr.uge.reddit.entity.UserEntity;
import fr.uge.reddit.entity.UserRoles;
import fr.uge.reddit.repository.MessageRepository;
import fr.uge.reddit.repository.TopicRepository;
import fr.uge.reddit.repository.UserRepository;
import fr.uge.reddit.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class RedditApplication {

    @Bean
    public CommandLineRunner initAdmin(UserService userService, TopicRepository postRepo, UserRepository userRepo, MessageRepository msgRepository){
        return args -> {
            userService.createNewUserAccount(new UserEntity("admin","admin", UserRoles.ADMIN)); // TODO: mettre dans les logins dans properties
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(RedditApplication.class, args);
    }
}
