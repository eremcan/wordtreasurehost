package com.word.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountCredentials {

    private String username;
    private String password;
    private String firstname;
    private String surname;
    private String usermail;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserMail() {
        return usermail;
    }

    public void setUserMail(String userMail) {
        this.usermail = userMail;
    }

    String getUsername() { return username; }
    String getPassword() { return password; }


    public void setUsername(String _username) { this.username = _username; }
    public void setPassword(String _password) { this.password = _password; }
}
