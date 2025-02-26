package com.kasamoke.DayPilot.service;

import com.kasamoke.DayPilot.model.UserDTO;
import com.kasamoke.DayPilot.model.UserModel;
import com.kasamoke.DayPilot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Optional<UserModel> createUser(UserModel user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            UserModel newUser = userRepository.save(user);
            return Optional.of(newUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<UserDTO> getUser(UUID id) {
        try {
            Optional<UserModel> response = userRepository.findById(id);
            if(response.isPresent()) {
                UserModel userModelData = response.get();
                UserDTO userDTO = new UserDTO(userModelData.getId(), userModelData.getEmail(), userModelData.getName());
                return Optional.of(userDTO);
            }
            else throw new UsernameNotFoundException("Invalid id");
        } catch (UsernameNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving the user", e);
        }
    }

    public Map<String, Object> loginUser(UserModel user) {
        Map<String, Object> obj = new HashMap<>();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(user.getEmail());
                UUID id = userRepository.findByEmail(user.getEmail()).getId();
                obj.put("token", token);
                obj.put("id", id);
            } else {
                throw new UsernameNotFoundException("Invalid email");
            }
        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException("Invalid credentials");
        }
        return obj;
    }
}
