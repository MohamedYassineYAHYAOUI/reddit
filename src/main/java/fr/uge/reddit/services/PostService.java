package fr.uge.reddit.services;

import fr.uge.reddit.entity.Post;
import fr.uge.reddit.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

@Service
public class PostService {
    @Autowired
    PostServiceWithFailure postServiceWithFailure;

    @PersistenceUnit
    EntityManagerFactory emf;

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void createNewPost(Post post){
        var retry=true;
        while(retry) {
            retry=false;
            try {
                postServiceWithFailure.createNewPostWrong(post);
            } catch (org.springframework.orm.ObjectOptimisticLockingFailureException e){
                retry=true;
            }
        }
    }
}
