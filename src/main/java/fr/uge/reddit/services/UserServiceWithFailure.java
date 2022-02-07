package fr.uge.reddit.services;

import fr.uge.reddit.entity.UserEntity;
import fr.uge.reddit.repository.TopicRepository;
import fr.uge.reddit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceWithFailure{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //@Autowired
    //CustomUserDetailsService


    @Transactional
    public void createUserWithFailure(UserEntity user) throws IllegalArgumentException{
        var userSameLogin = userRepository.findByLogin(user.getLogin());
        if(userSameLogin != null){
            throw new IllegalArgumentException("Login is not available");
        }
        userRepository.save(new UserEntity(user.getLogin(),passwordEncoder.encode(user.getPassword()), user.getUserRole()));
    }

    @Transactional
    public UserEntity findUserWithFailure(String login) throws IllegalArgumentException{
        var userFind = userRepository.findByLogin(login);
        if(userFind == null){
            throw new IllegalArgumentException("User not found");
        }
        return userFind;
    }

    @Transactional
    public Optional<UserEntity> findUserByIdWithFailure(long id) throws IllegalArgumentException{
        return userRepository.findById(id);
    }

    @Transactional
    public void updateUserPasswordWithFailure(String login, String newPassword) throws IllegalArgumentException{
        var userFind = userRepository.findByLogin(login);
        if(userFind == null){
            throw new IllegalArgumentException("User not found for update password");
        }
        userFind.setPassword(passwordEncoder.encode(newPassword));
    }

    @Transactional
    public boolean checkUserOldPasswordMatchWithFailure(String login, String oldPassword) throws IllegalArgumentException{
        var userFind = userRepository.findByLogin(login);
        if(userFind == null){
            throw new IllegalArgumentException("User not found for check password");
        }
        return passwordEncoder.matches(oldPassword, userFind.getPassword());
    }
}
