package fr.uge.reddit.entity;

public enum UserRoles {
    USER("User"),
    ADMIN("Admin");

    private String role;


    UserRoles(String roleName){
        this.role = roleName;
    }

    public String getRole() {
        return role;
    }
}
