package fr.uge.reddit.dto;

import javax.validation.constraints.NotBlank;

public class CredentialsDTO {
    @NotBlank(message = "login must not be blank")
    private String login;
    @NotBlank(message = "password must not be blank")
    private String password;

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
}
