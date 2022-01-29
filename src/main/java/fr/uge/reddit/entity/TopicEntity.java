package fr.uge.reddit.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Topics")
public class TopicEntity {

    @Id
    @GeneratedValue(generator = "topic_gen")
    @Column(name="TOPICID")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MESSAGEID")
    private MessageEntity message;

    @Column(name="TITLE")
    private String title;

    public TopicEntity(){}

    public TopicEntity(MessageEntity message, String title) {
        this.message = message;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public MessageEntity getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(MessageEntity message) {
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
