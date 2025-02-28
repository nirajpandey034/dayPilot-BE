package com.kasamoke.DayPilot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class GoalTrackerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Goal Id is mandatory")
    private UUID goalId;

    @NotNull(message = "Date is mandatory")
    private LocalDate date;

    private boolean status;

    public GoalTrackerModel() {
    }

    public GoalTrackerModel(UUID id, UUID goalId, LocalDate date, boolean status) {
        this.id = id;
        this.goalId = goalId;
        this.date = date;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getGoalId() {
        return goalId;
    }

    public void setGoalId(UUID goalId) {
        this.goalId = goalId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
