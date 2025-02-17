package com.kasamoke.DayPilot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/goal")
public class GoalController {

    @GetMapping({"", "/"})
    public ResponseEntity<?> defaultHandler() {
        return ResponseEntity.status(200).body("Welcome to Day Pilot");
    }
}
