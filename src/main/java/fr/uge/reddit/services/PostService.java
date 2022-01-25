package fr.uge.reddit.services;

import fr.uge.reddit.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostService {
    @Autowired
    PostServiceWithFailure postServiceWithFailure;

    @PersistenceUnit
    EntityManagerFactory emf;

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void createNewPost(PostEntity post){
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

    public List<PostEntity> getAllPosts(){
        var posts = StreamSupport
                .stream(postServiceWithFailure.getPostRepository().findAll().spliterator(), false)
                .collect(Collectors.toList());
        return posts;
    }

    public Optional<PostEntity> getPost(long id){
        var post = postServiceWithFailure.getPostRepository().findById(id);
        return post;
    }
}
