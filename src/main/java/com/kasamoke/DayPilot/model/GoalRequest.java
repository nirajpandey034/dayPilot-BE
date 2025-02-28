package com.kasamoke.DayPilot.model;

import java.time.LocalDate;
import java.util.UUID;

public class GoalRequest {
    private String title;
    private String description;
    private String frequency;
    private LocalDate startDate;
    private LocalDate targetDate;
    private boolean completionStatus;
    private UUID ownerId;

    public GoalRequest() {
    }

    public GoalRequest(String title, String description, String frequency, LocalDate startDate, LocalDate targetDate, boolean completionStatus, UUID ownerId) {
        this.title = title;
        this.description = description;
        this.frequency = frequency;
        this.startDate = startDate;
        this.targetDate = targetDate;
        this.completionStatus = completionStatus;
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(boolean completionStatus) {
        this.completionStatus = completionStatus;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }
}

