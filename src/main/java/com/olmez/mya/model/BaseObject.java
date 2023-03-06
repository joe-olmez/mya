package com.olmez.mya.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "id")
public class BaseObject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected boolean deleted = false;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof BaseObject)) {
            return false;
        }
        BaseObject bo = (BaseObject) obj;
        return (bo.getId() != null) && (this.id != null) && (this.id.equals(bo.getId()));
    }

    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : 0;
    }

    public boolean isDeleted() {
        return deleted;
    }

}
