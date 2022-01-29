package fr.uge.reddit.services;

import fr.uge.reddit.entity.UserEntity;
import fr.uge.reddit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceWithFailure{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    public void createUserWithFailure(UserEntity user) throws IllegalArgumentException{
        var userSameLogin = userRepository.findByLogin(user.getLogin());
        if(userSameLogin != null){
            throw new IllegalArgumentException("Login is not available");
        }
        userRepository.save(new UserEntity(user.getLogin(),passwordEncoder.encode(user.getPassword()), user.getUserRole()));
    }

}
