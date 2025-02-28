package com.kasamoke.DayPilot.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class GoalModel {

    public enum Frequency {
        DAILY,
        WEEKLY,
        MONTHLY,
        QUARTERLY,
        HALF_YEARLY,
        ANNUALLY
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Start date is mandatory")
    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDate startDate;

    @NotNull(message = "Target date is mandatory")
    @Future(message = "Target date must be in the future")
    private LocalDate targetDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Frequency is mandatory")
    private Frequency frequency;

    private boolean completionStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonSerialize(using = UserModelSerializer.class)
    @NotNull(message = "Owner is mandatory")
    private UserModel owner;

    public GoalModel() {
    }

    public GoalModel(UUID id, String title, String description, LocalDate startDate, LocalDate targetDate, Frequency frequency, boolean completionStatus, UserModel owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.targetDate = targetDate;
        this.frequency = frequency;
        this.completionStatus = completionStatus;
        this.owner = owner;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public boolean isCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(boolean completionStatus) {
        this.completionStatus = completionStatus;
    }

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }
}
