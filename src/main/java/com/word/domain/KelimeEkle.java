package com.word.domain;

import javax.persistence.*;

/**
 * Created by Aytan on 27.05.2017.
 */
@Entity
@Table(name = "kelimeekle")
public class KelimeEkle extends BaseEntity{
    @Column
    private String eklenenKelimeKey;
    @Column
    private String eklenenKelimeValue;
    @Column
    private Long kisiId;

    public String getEklenenKelimeKey() {
        return eklenenKelimeKey;
    }

    public void setEklenenKelimeKey(String eklenenKelimeKey) {
        this.eklenenKelimeKey = eklenenKelimeKey;
    }

    public String getEklenenKelimeValue() {
        return eklenenKelimeValue;
    }

    public void setEklenenKelimeValue(String eklenenKelimeValue) {
        this.eklenenKelimeValue = eklenenKelimeValue;
    }

    public Long getKisiId() {
        return kisiId;
    }

    public void setKisiId(Long kisiId) {
        this.kisiId = kisiId;
    }
}
