package com.hockeydb.hockeydb.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    @Getter(AccessLevel.NONE)
    private UUID userId;

    @Column(name = "username", columnDefinition = "bpchar")
    private String userName;

    @Column(columnDefinition = "bpchar")
    private String password;

    @Column(columnDefinition = "bpchar")
    private String email;

    @Column(columnDefinition = "bpchar")
    private String token;

    public User(String username, String password, String email) {
        this.userName = username;
        this.password = password;
        this.email = email;
    }

    public UUID getID() {
        return userId;
    }
}
