package fr.uge.reddit.services;

import fr.uge.reddit.entity.User;
import fr.uge.reddit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;
/*
    @PersistenceUnit
    EntityManagerFactory emf;

    @PersistenceContext
    EntityManager em;
*/
    @Retryable(value = ObjectOptimisticLockingFailureException.class)
    @Transactional
    public void addUserToDB(User user){
        //userRepository.save(new User(user.getLogin(), user.getPassword(), user.isAdmin()));
        var userToAdd = userRepository.findByLogin(user.getLogin());
        if(userToAdd == null){
            userRepository.save(new User(user.getLogin(), user.getPassword(), user.isAdmin()));
        }else{
            throw new IllegalArgumentException("User exist in database");
        }
    }


}
