package com.word.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Nahide on 29.03.2017.
 */

@Entity
@Table(name = "userscore")
public class UserScore extends BaseEntity {

    @Column(name = "userid")
    private Long userid;

    @Column(name = "bildigikelimetoplami")
    private Long userBildigiKelimeToplami;


    public UserScore() {
        super();
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getUserBildigiKelimeToplami() {
        return userBildigiKelimeToplami;
    }

    public void setUserBildigiKelimeToplami(Long userBildigiKelimeToplami) {
        this.userBildigiKelimeToplami = userBildigiKelimeToplami;
    }


}
