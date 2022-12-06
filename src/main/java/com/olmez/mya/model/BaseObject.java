package com.olmez.mya.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "id")
public class BaseObject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime createdTime = LocalDateTime.now();
    private LocalDateTime lastModifiedTime;
    private boolean deleted = false;

    @ManyToOne
    private User lastModifiedBy;

    @PreUpdate
    public void onPreUpdate() {
        lastModifiedTime = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BaseObject)) {
            return false;
        }
        BaseObject other = (BaseObject) obj;
        return (other.getId() != null) && (id != null) && (id.equals(other.getId()));
    }

    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : 0;
    }

    public boolean isDeleted() {
        return deleted;
    }

}
