package com.olmez.mya.model;

import org.checkerframework.checker.nullness.qual.Nullable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ApiToken extends BaseObject {

    @Column(length = 96)
    private @Nullable String tokenLabel;
    private @Nullable String tokenHash;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
