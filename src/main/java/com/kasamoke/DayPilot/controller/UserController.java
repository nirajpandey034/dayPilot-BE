package com.kasamoke.DayPilot.controller;

import com.kasamoke.DayPilot.model.UserDTO;
import com.kasamoke.DayPilot.model.UserModel;
import com.kasamoke.DayPilot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register/")
    public ResponseEntity<?> createUser(@RequestBody UserModel user) {
        try{
            Optional<UserModel> response = userService.createUser(user);
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/login/")
    public ResponseEntity<?> loginUser(@RequestBody UserModel user) throws Exception {
        try{
            Map<String, Object> response = userService.loginUser(user);
            return ResponseEntity.status(200).body(response);
        }
        catch (AuthenticationException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable UUID id) {
        try {
            Optional<UserDTO> response = userService.getUser(id);
            return ResponseEntity.status(200).body(response);
        } catch (UsernameNotFoundException e){
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
