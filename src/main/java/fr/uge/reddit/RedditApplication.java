package fr.uge.reddit;


import fr.uge.reddit.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedditApplication {

    @Bean
    public CommandLineRunner initAdmin(){
        return args -> {

            //User admin = new User("admin",);

        };


    }

    public static void main(String[] args) {
        SpringApplication.run(RedditApplication.class, args);}

}
