package com.kasamoke.DayPilot.controller;

import com.kasamoke.DayPilot.model.UserModel;
import com.kasamoke.DayPilot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserModel user) {
        try{
            Optional<UserModel> response = userService.createUser(user);
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable UUID id) {
        try {
            Optional<UserModel> response = userService.getUser(id);
            return ResponseEntity.status(200).body(response);
        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
