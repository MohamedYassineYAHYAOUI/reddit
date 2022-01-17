package fr.uge.reddit.repository;

import fr.uge.reddit.dto.PostDTO;
import fr.uge.reddit.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
}
