package model;

import lombok.Getter;

@Getter
public class UserInfo {
    private String username;
    private String role; // "admin" or "user"

    public UserInfo(String username, String role) {
        this.username = username;
        this.role = role;
    }
}
