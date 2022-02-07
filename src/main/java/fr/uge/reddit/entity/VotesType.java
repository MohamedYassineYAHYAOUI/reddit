package fr.uge.reddit.entity;

public enum VotesType {


    UPVOTE("upVote"),
    DOWNVOTE("downVote");

    private String votetype;


    VotesType (String type){
        this.votetype = type;
    }

    public String getVote() {
        return votetype;
    }


}
