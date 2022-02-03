package fr.uge.reddit.services;

import fr.uge.reddit.entity.TopicEntity;
import fr.uge.reddit.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public static UserEntity currentUser() {
        return (UserEntity) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public Optional<UserEntity> getUser(long id){
        return userServiceWithFailure.getUserRepository().findById(id);
    }

    public List<TopicEntity> getTopicsByUser(long userId){
       return topicServiceWithFailure.getTopicRepository().findTopicByUserId(userId);
    }
}
