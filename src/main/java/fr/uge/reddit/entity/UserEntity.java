package fr.uge.reddit.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="User")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(generator = "user_gen")
    @Column(name="USERID")
    private Long id;

    @Column(name="LOGIN")
    private String login;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="USER_ROLE")
    @Enumerated(EnumType.STRING)
    private UserRoles userRole;

    @OneToMany(cascade = {CascadeType.ALL} )
    @JoinColumn(name = "user_ids" )
    private List<VotesEntity> votes;


    public UserEntity(){}

    public UserEntity(String login, String password, UserRoles userRole){
        this.login = Objects.requireNonNull(login);
        this.password = Objects.requireNonNull(password);
        this.userRole = Objects.requireNonNull(userRole);
    }

    public List<VotesEntity> getVotes() {
        return votes;
    }

    public void setVotes(List<VotesEntity> votes) {
        this.votes = votes;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }



    public UserRoles getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoles userRole) {
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(userRole.getRole());
        return Collections.singletonList(auth);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
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


}
