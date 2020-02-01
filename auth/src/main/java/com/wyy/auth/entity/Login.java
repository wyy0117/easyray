package com.wyy.auth.entity;

/**
 * @Date: 20-2-1
 * @Author: wyy
 */
public class Login {
    private String username;
    private String password;
    private Boolean rememberMe;


    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rememberMe=" + rememberMe +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public Login setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Login setPassword(String password) {
        this.password = password;
        return this;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public Login setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
        return this;
    }
}
