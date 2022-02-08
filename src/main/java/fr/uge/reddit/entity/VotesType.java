package fr.uge.reddit.entity;

public enum VotesType {

    UPVOTE("UpVote"),
    DOWNVOTE("DownVote");

    private String role;


    VotesType(String roleName){
        this.role = roleName;
    }

    public String getRole() {
        return role;
    }
}

