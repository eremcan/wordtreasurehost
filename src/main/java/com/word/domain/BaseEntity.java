package com.word.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Nahide on 09.02.2017.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

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
