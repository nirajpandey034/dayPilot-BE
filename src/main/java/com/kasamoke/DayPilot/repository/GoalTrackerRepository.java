package com.kasamoke.DayPilot.repository;

import com.kasamoke.DayPilot.model.GoalTrackerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GoalTrackerRepository extends JpaRepository<GoalTrackerModel, UUID> {
}
