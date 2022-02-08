package fr.uge.reddit.entity;

import javax.persistence.*;


@Entity
public class VotesEntity {
    @Id
    @GeneratedValue(generator = "vote_")
    @Column(name="VOTEID")
    private Long id;

    @Column(name="MessageID")
    private long messageId;

    @Enumerated(EnumType.STRING)
    @Column(name="VOTE")
    private VotesType vote;

    public VotesEntity(){}

    public VotesEntity(long messageId, VotesType vote){
        this.messageId = messageId;
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
        return messageId;
    }

    public void setPostId(long postId) {
        this.messageId = postId;
    }

}
