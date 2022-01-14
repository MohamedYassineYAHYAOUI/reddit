package fr.uge.reddit.entity;


import javax.persistence.*;

@Entity
@Table(name="Posts")
public class Post {

    @Id
    @GeneratedValue(generator = "post_gen")
    @Column(name="POSTID")
    private Long id;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MESSAGEID")
    private Message message;


    @Column(name="TITLE")
    private String title;

    public Post(){}


    public Long getId() {
        return id;
    }

    public Message getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
