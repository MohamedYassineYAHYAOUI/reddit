package fr.uge.reddit.services;

import fr.uge.reddit.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;


@Service
public class UserService {

    @Autowired
    private UserServiceWithFailure userServiceWithFailure;

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




}
