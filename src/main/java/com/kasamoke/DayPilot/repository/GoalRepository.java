package com.kasamoke.DayPilot.repository;

import com.kasamoke.DayPilot.model.GoalModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GoalRepository extends JpaRepository<GoalModel, UUID> {

    List<GoalModel> findByOwnerId(UUID ownerId);
}
