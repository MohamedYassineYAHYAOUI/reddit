package fr.uge.reddit.services;

import fr.uge.reddit.entity.TopicEntity;
import fr.uge.reddit.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserServiceWithFailure userServiceWithFailure;

    @Autowired
    private TopicServiceWithFailure topicServiceWithFailure;

    @Transactional
    public void createNewUserAccount(UserEntity user) throws IllegalArgumentException{
        var retry = true;
        while(retry){
            retry=false;
            try{
                userServiceWithFailure.createUserWithFailure(user);
            }catch(ObjectOptimisticLockingFailureException e){
                retry = true;
            }
        }
    }

    @Transactional
    public UserEntity findUser(String login) throws IllegalArgumentException{
        var retry = true;
        UserEntity user = null;
        while(retry){
            retry=false;
            try{
                user = userServiceWithFailure.findUserWithFailure(login);
            }catch(ObjectOptimisticLockingFailureException e){
                retry = true;
            }
        }
        return user;
    }

    @Transactional
    public void updatePassword(String login, String password) throws IllegalArgumentException{
        var retry = true;
        while(retry){
            retry=false;
            try{
                userServiceWithFailure.updateUserPasswordWithFailure(login, password);
            }catch(ObjectOptimisticLockingFailureException e){
                retry = true;
            }
        }
    }

    @Transactional
    public boolean checkUserOldPasswordMatch(String login, String oldPassword) throws IllegalArgumentException {
        var retry = true;
        while (retry) {
            retry = false;
            try {
                return userServiceWithFailure.checkUserOldPasswordMatchWithFailure(login, oldPassword);
            } catch (ObjectOptimisticLockingFailureException e) {
                retry = true;
            }
        }
        return false;
    }

    @Transactional
    public UserEntity currentUser() {
        var user = (UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return findUser(user.getUsername());
    }

    public Optional<UserEntity> getUser(long id){
        return userServiceWithFailure.findUserByIdWithFailure(id);
    }

    public List<TopicEntity> getTopicsByUser(long userId){
       return topicServiceWithFailure.getTopicRepository().findTopicByUserId(userId);
    }
}
