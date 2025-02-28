package com.kasamoke.DayPilot.service;

import com.kasamoke.DayPilot.model.GoalTrackerModel;
import com.kasamoke.DayPilot.repository.GoalRepository;
import com.kasamoke.DayPilot.repository.GoalTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GoalTrackerService {

    @Autowired
    GoalTrackerRepository goalTrackerRepository;

    public void markGoal(GoalTrackerModel data) {
        try{
            goalTrackerRepository.save(data);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
