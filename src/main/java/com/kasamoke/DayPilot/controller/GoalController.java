package com.kasamoke.DayPilot.controller;

import com.kasamoke.DayPilot.model.GoalModel;
import com.kasamoke.DayPilot.model.GoalRequest;
import com.kasamoke.DayPilot.model.GoalTrackerModel;
import com.kasamoke.DayPilot.service.GoalService;
import com.kasamoke.DayPilot.service.GoalTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/goal")
public class GoalController {

    @Autowired
    GoalService goalService;

    @Autowired
    GoalTrackerService goalTrackerService;

    @PostMapping({"/create", "/create/"})
    public ResponseEntity<?> createGoal(@RequestBody GoalRequest goal) throws Exception {
        try {
            goalService.createGoal(goal);
            return ResponseEntity.status(200).body("Goal Created Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/myGoals/{id}")
    public ResponseEntity<?> getMyGoals(@PathVariable UUID id) {
        try {
            List<GoalModel> listOfGoals = goalService.getMyGoals(id);
            return ResponseEntity.status(200).body(listOfGoals);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/goals/{id}")
    public ResponseEntity<?> getScheduledGoals(@PathVariable UUID id) {
        try{
            List<GoalModel> goals = goalService.getScheduledGoals(id);
            return ResponseEntity.status(200).body(goals);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/mark")
    public ResponseEntity<?> markGoal(@RequestBody GoalTrackerModel goalTrackerData) {
        try {
            goalTrackerService.markGoal(goalTrackerData);
            return ResponseEntity.status(200).body("Goal Marked");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
