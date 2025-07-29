package com.hockeydb.hockeydb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hockeydb.hockeydb.data.SignupReq;
import com.hockeydb.hockeydb.model.User;
import com.hockeydb.hockeydb.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(SignupReq req) throws Exception {
        String email = req.getEmail();
        Optional<User> existingUser = userRepo.findByEmail(email);

        if (existingUser.isPresent()) {
            System.out.println(existingUser.get().getUserName());
            System.out.println(existingUser.get().getEmail());

            throw new Exception(String.format("User with the email address '%s' already exists.", email));
        }

        String hashedPassword = passwordEncoder.encode(req.getPassword());
        User user = new User(req.getUsername(), hashedPassword, email);
        userRepo.add(user);
    }
}
