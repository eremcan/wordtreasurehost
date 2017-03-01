package com.word.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * Created by Nahide on 09.02.2017.
 */

@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @NotNull
    @Column(name = "firstname")
    private String firstname;

    @NotNull
    @Column(name = "surname")
    private String surname;
    @NotNull
    @Column(name = "username")
    private String userName;
    @NotNull
    @Column(name = "password")
    private String userPassword;

    @NotNull
    @Column(name = "usermail")
    private String userMail;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<BilinenKelime> userBilinenKelime;

    public User() {
        super();
    }


    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }


}
