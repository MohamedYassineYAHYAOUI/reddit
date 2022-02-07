package fr.uge.reddit.dto;

import fr.uge.reddit.entity.UserRoles;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;


public class CredentialsDTO  {

    @NotBlank(message = "Login must not be blank")
    private String login;
    @NotBlank(message = "Password must not be blank")
    private String password;

    private List<VotesDTO> votes;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<VotesDTO> getVotes() {
        return votes;
    }

    public void setVotes(List<VotesDTO> votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof CredentialsDTO)) return false;
        CredentialsDTO credentials = (CredentialsDTO) o;
        return Objects.equals(login, credentials.login) && Objects.equals(password, credentials.password);
    }

    @Override
    public int hashCode() {
        return (login + password).hashCode();
    }
}
