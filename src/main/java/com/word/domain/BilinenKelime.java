package com.word.domain;

import javax.persistence.*;

/**
 * Created by Nahide on 10.02.2017.
 */
@Entity
@Table(name = "bilinenkelime")
public class BilinenKelime extends BaseEntity {


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kelime_id", referencedColumnName = "id")
    private Kelime kelime;

    public BilinenKelime() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Kelime getKelime() {
        return kelime;
    }

    public void setKelime(Kelime kelime) {
        this.kelime = kelime;
    }
}
