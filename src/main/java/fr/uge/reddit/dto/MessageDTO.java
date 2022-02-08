package fr.uge.reddit.dto;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

public class MessageDTO {

    @NotBlank
    private String body;

    private int score;

    private String author;

    private Date timeStamp;

    private List<MessageDTO> replies;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
}
