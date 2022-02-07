package fr.uge.reddit.dto;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TopicDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String body;

    private int score;

    private String author;

    private Date timeStamp;

    private List<MessageDTO> replies;



    public TopicDTO(String title, String body, int score, String author, Date timeStamp, List<MessageDTO> replies) {
        this.title = title;
        this.body = body;
        this.score = score;
        this.author = author;
        this.timeStamp = timeStamp;
        this.replies = replies;
    }
    public TopicDTO(){
        this(null,null,0,null, null, new ArrayList<>());
    };

    public TopicDTO(String title, String body,List<MessageDTO> replies){
        this(title, body, 0,null,null, new ArrayList<>());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getScore() {
        return score;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<MessageDTO> getReplies() {
        return replies;
    }

    public void setReplies(List<MessageDTO> replies) {
        this.replies = replies;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
