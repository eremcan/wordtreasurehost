package com.word.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Nahide on 09.02.2017.
 */

@Entity
@Table(name = "user")
public class User extends BaseEntity {




    @NotNull
    @Column(name = "username")
    private String userName;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<BilinenKelime> userBilinenKelime;

    public User() {
        super();
    }

   /* @ManyToMany(fetch =FetchType.EAGER)
    @JoinTable(name = "join_bilinenkelimeler ",joinColumns = {@JoinColumn(name = "userid")},inverseJoinColumns = {@JoinColumn(name = "kelimeid")})
    public List<Kelime> getKelime() {
        return kelime;

    } */


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
