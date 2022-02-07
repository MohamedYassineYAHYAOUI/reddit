package fr.uge.reddit;


import fr.uge.reddit.entity.MessageEntity;
import fr.uge.reddit.entity.TopicEntity;
import fr.uge.reddit.entity.UserEntity;
import fr.uge.reddit.entity.UserRoles;
import fr.uge.reddit.repository.MessageRepository;
import fr.uge.reddit.repository.TopicRepository;
import fr.uge.reddit.repository.UserRepository;
import fr.uge.reddit.services.UserService;
import fr.uge.reddit.services.VoteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class RedditApplication {

    @Bean
    public CommandLineRunner initAdmin(UserService userService, TopicRepository postRepo, UserRepository userRepo, MessageRepository msgRepository){
        return args -> {
            userService.createNewUserAccount(new UserEntity("admin","admin", UserRoles.ADMIN)); // TODO: mettre dans les logins dans properties

            /*var user =new UserEntity("momo","test", UserRoles.USER);
            userRepo.save(user);


            var post_msg = new MessageEntity(user,"post", null);

            var msg1 = new MessageEntity(user,"reply 1", null);
            var msg2 = new MessageEntity(user,"reply 2", null);
            msgRepository.save(msg1);
            msgRepository.save(msg2);

            var post_entity = new TopicEntity(post_msg, "test post");
            postRepo.save(post_entity);
            var msg1_1 = new MessageEntity(user,"reply 1.1", null);
            //msgRepository.save(post_msg);

            msgRepository.save(msg1_1);

            post_msg.setReplies(List.of(
                    msg1, msg2
            ));
            msg1.setReplies(List.of(
                    msg1_1
            ));
            */
        };
    }

    @Bean
    public CommandLineRunner cmd(VoteService voteservice,UserRepository userRepo, TopicRepository postRepo,MessageRepository msgRepository){
        return args -> {
            var user =new UserEntity("User","userpw", UserRoles.USER);
            userRepo.save(user);

            var post_msg = new MessageEntity(user,"post", null);
            var msg1 = new MessageEntity(user,"reply 1", null);
            var msg2 = new MessageEntity(user,"reply 2", null);
            msgRepository.save(msg1);
            msgRepository.save(msg2);
            var post_entity = new TopicEntity(post_msg, "test post");
            postRepo.save(post_entity);

/*
            var threads = new ArrayList<Thread>();
            for (int i = 0; i < 100; i++) {
                var thread=new Thread(() -> {
                    for (int j = 0; j < 100; j++) {
                        var name = ""+ ThreadLocalRandom.current().nextInt(100);
                        userRepo.save(new UserEntity("User"+name,"aaa", UserRoles.USER));
                        voteservice.incrementVote(msg1);
                    }
                });

                threads.add(thread);
                thread.start();
            }
            for(var thread : threads){
                thread.join();
            }
            //System.out.println(voteservice.totalCountVote());*/
        };

    }
    public static void main(String[] args) {
        SpringApplication.run(RedditApplication.class, args);}

}
