package fr.uge.reddit.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "MESSAGE")
public class MessageEntity {

    @Id
    @GeneratedValue(generator = "message_gen")
    @Column(name="MESSAGEID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USERID")
    private UserEntity author;

    @Column(name="BODY")
    private String body;

    @Column(name="SCORE")
    private int score;

    @Column(name="TIMESTAMP")
    private Date timeStamp;

    //TODO :we can inmplement Message parent with field Message parent @Manyto One (cf voir contexte reddit)
    @OneToMany(cascade = CascadeType.ALL) // a vérifier si all ou DELEt
    @JoinColumn(name="REPLYID")
    private List<MessageEntity> replies;

    public Long getId() {
        return id;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    public int getScore() {
        return score;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public List<MessageEntity> getReplies() {
        return replies;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setReplies(List<MessageEntity> replies) {
        this.replies = replies;
    }
}
