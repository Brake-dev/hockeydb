package com.hockeydb.hockeydb.repository;

import com.hockeydb.hockeydb.model.User;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class UserRepository {

    private static final String INSERT = "INSERT INTO \"user\" (user_id, username, email, password) VALUES (:user_id, :username, :email, :password)";
    private static final String FIND_BY_EMAIL = "SELECT * FROM \"user\" WHERE email = :email";

    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void add(User user) {
        long affected = jdbcClient.sql(INSERT)
                .param("user_id", UUID.randomUUID())
                .param("username", user.getUserName())
                .param("email", user.getEmail())
                .param("password", user.getPassword())
                .update();

        Assert.isTrue(affected == 1, "Could not add user.");
    }

    public Optional<User> findByEmail(String email) {
        return jdbcClient.sql(FIND_BY_EMAIL)
                .param("email", email)
                .query(User.class)
                .optional();
    }
}