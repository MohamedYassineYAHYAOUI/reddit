package fr.uge.reddit.services;

import fr.uge.reddit.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
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
    public boolean checkUserOldPasswordMatch(String login, String oldPassword) throws IllegalArgumentException{
        var retry = true;
        while(retry){
            retry=false;
            try{
                return userServiceWithFailure.checkUserOldPasswordMatchWithFailure(login, oldPassword);
            }catch(ObjectOptimisticLockingFailureException e){
                retry = true;
            }
        }
        return false;
    }
}
