package fr.uge.reddit.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="User")
public class User {

    @Id
    @GeneratedValue(generator = "user_gen")
    @Column(name="USERID")
    private Long id;

    @Column(name="LOGIN")
    private String login;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="ISADMIN")
    private boolean isAdmin;

    public User(){}

    public User(String login, String password, Boolean isAdmin){
        this.login = Objects.requireNonNull(login);
        this.password = Objects.requireNonNull(password);
        this.isAdmin = isAdmin;
    }


    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
