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

import java.util.Date;


@SpringBootApplication
public class RedditApplication {

    @Bean
    public CommandLineRunner initAdmin(UserService userService, TopicRepository postRepo, UserRepository userRepo, MessageRepository msgRepository){
        return args -> {
            userService.createNewUserAccount(new UserEntity("admin","admin", UserRoles.ADMIN)); // TODO: mettre dans les logins dans properties
        };
    }

    @Bean
    public CommandLineRunner cmd(UserRepository userRepo, TopicRepository postRepo,MessageRepository msgRepository){
        return args -> {
            var user =new UserEntity("User","userpw", UserRoles.USER);
            userRepo.save(user);

            var post_msg = new MessageEntity(user,"post", new Date());
            var msg1 = new MessageEntity(user,"reply 1", new Date());
            var msg2 = new MessageEntity(user,"reply 2", new Date());
            msgRepository.save(msg1);
            msgRepository.save(msg2);
            var post_entity = new TopicEntity(post_msg, "test post 1");
            postRepo.save(post_entity);

            post_msg = new MessageEntity(user,"post", new Date());
            post_entity = new TopicEntity(post_msg, "test post 2");
            postRepo.save(post_entity);

            post_msg = new MessageEntity(user,"post", new Date());
            post_entity = new TopicEntity(post_msg, "test post 3");
            postRepo.save(post_entity);

            post_msg = new MessageEntity(user,"post", new Date());
            post_entity = new TopicEntity(post_msg, "test post 4");
            postRepo.save(post_entity);

            post_msg = new MessageEntity(user,"post", new Date());
            post_entity = new TopicEntity(post_msg, "test post 5");
            postRepo.save(post_entity);

            post_msg = new MessageEntity(user,"post", new Date());
            post_entity = new TopicEntity(post_msg, "test post 6");
            postRepo.save(post_entity);
            post_msg = new MessageEntity(user,"post", new Date());
            post_entity = new TopicEntity(post_msg, "test post 7");
            postRepo.save(post_entity);

            post_msg = new MessageEntity(user,"post", new Date());
            post_entity = new TopicEntity(post_msg, "test post 8");
            postRepo.save(post_entity);

            post_msg = new MessageEntity(user,"post", new Date());
            post_entity = new TopicEntity(post_msg, "test post 9");
            postRepo.save(post_entity);

            post_msg = new MessageEntity(user,"post", new Date());
            post_entity = new TopicEntity(post_msg, "test post 10");
            postRepo.save(post_entity);

            post_msg = new MessageEntity(user,"post", new Date());
            post_entity = new TopicEntity(post_msg, "test post 11");
            postRepo.save(post_entity);

            post_msg = new MessageEntity(user,"post", new Date());
            post_entity = new TopicEntity(post_msg, "test post 12");
            postRepo.save(post_entity);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(RedditApplication.class, args);
    }
}
