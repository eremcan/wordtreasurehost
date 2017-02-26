package com.word.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Nahide on 09.02.2017.
 */

@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @NotNull
    @Column(name = "isim")
    private String userorjName;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "username")
    private String userName;

    @NotNull
    @Column(name = "usermail")
    private String userMail;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<BilinenKelime> userBilinenKelime;

    public User() {
        super();
    }

    public String getUserorjName() {
        return userorjName;
    }

    public void setUserorjName(String userorjName) {
        this.userorjName = userorjName;
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
