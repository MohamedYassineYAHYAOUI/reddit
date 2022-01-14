package fr.uge.reddit.entity;


import javax.persistence.*;

@Entity
@Table(name="Posts")
public class PostEntity {

    @Id
    @GeneratedValue(generator = "post_gen")
    @Column(name="POSTID")
    private Long id;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MESSAGEID")
    private MessageEntity message;


    @Column(name="TITLE")
    private String title;

    public PostEntity(){}


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
