package fr.uge.reddit;


import fr.uge.reddit.entity.UserEntity;
import fr.uge.reddit.entity.UserRoles;
import fr.uge.reddit.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedditApplication {

    @Bean
    public CommandLineRunner initAdmin(UserService userService){
        return args -> {
            userService.createNewUserAccount(new UserEntity("admin","admin", UserRoles.ADMIN)); // TODO: mettre dans les logins dans properties
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(RedditApplication.class, args);
    }
}
