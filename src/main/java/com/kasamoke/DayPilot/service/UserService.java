package com.kasamoke.DayPilot.service;

import com.kasamoke.DayPilot.model.UserModel;
import com.kasamoke.DayPilot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<UserModel> createUser(UserModel user) {
        try {
            UserModel newUser = userRepository.save(user);
            return Optional.of(newUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<UserModel> getUser(UUID id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
