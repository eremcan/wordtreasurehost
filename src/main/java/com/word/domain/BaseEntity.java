package com.word.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by Nahide on 09.02.2017.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @SuppressWarnings("serial")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || this == null || this.id == null) {
            return super.equals(obj);
        }
        return this.id.equals(((BaseEntity) obj).getId());
    }
}
