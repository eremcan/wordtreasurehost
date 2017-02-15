package com.word.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Nahide on 10.02.2017.
 */
@Entity
@Table(name = "bilinenkelime")
public class BilinenKelime extends BaseEntity {




    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @ManyToOne
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
