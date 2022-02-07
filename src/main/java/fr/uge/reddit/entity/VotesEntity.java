package fr.uge.reddit.entity;

import javax.persistence.*;


@Entity
public class VotesEntity {
    @Id
    @GeneratedValue(generator = "vote_")
    @Column(name="VOTEID")
    private Long id;

    @Column(name="POSTID")
    private long postId;

    @Column(name="VOTE")
    private VotesType vote;

    public VotesEntity(){}

    public VotesEntity(long postId, VotesType vote){
        this.postId = postId;
        this.vote = vote;
    }
    public VotesType getVote() {
        return vote;
    }

    public void setVote(VotesType vote) {
        this.vote = vote;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

}
