package fr.uge.reddit.dto;


import fr.uge.reddit.entity.VotesType;

public class VotesDTO {
    private Long id;

    private long postId;

    private VotesType vote;

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
