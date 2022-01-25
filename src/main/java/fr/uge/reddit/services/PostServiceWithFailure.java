package fr.uge.reddit.services;

import fr.uge.reddit.entity.PostEntity;
import fr.uge.reddit.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostServiceWithFailure {
    @Autowired
    PostRepository postRepository;

    @Transactional
    public void createNewPostWrong(PostEntity post){
        postRepository.save(post);
    }

    public PostRepository getPostRepository() {
        return postRepository;
    }
}