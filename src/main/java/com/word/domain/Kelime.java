package com.word.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Nahide on 09.02.2017.
 */
@Entity
@Table(name = "kelime")
public class Kelime extends BaseEntity {

    @NotNull
    @Column(name = "kelime_key")
    private String kelimeKey;

    @NotNull
    @Column(name = "kelime_value")
    private String kelimeValue;

    @NotNull
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kelime")
    private Set<BilinenKelime> bilinenKelimeList;

    public Kelime() {
        super();
    }

    public String getKelimeKey() {
        return kelimeKey;
    }

    public void setKelimeKey(String kelimeKey) {
        this.kelimeKey = kelimeKey;
    }

    public String getKelimeValue() {
        return kelimeValue;
    }

    public void setKelimeValue(String kelimeValue) {
        this.kelimeValue = kelimeValue;
    }

    public Set<BilinenKelime> getBilinenKelimeList() {
        return bilinenKelimeList;
    }

    public void setBilinenKelimeList(Set<BilinenKelime> bilinenKelimeList) {
        this.bilinenKelimeList = bilinenKelimeList;
    }

    /* @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "join_bilinenkelimeler ", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "id")})
    public List<User> getUsers() {
        return users;
    } */


}
