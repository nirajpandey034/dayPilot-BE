package com.kasamoke.DayPilot.service;

import com.kasamoke.DayPilot.model.*;
import com.kasamoke.DayPilot.repository.GoalRepository;
import com.kasamoke.DayPilot.repository.GoalTrackerRepository;
import com.kasamoke.DayPilot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GoalService {

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GoalTrackerRepository goalTrackerRepository;

    public void createGoal(GoalRequest goalRequest) {
        try{
            UserModel owner = userRepository.findById(goalRequest.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
            GoalModel goal = new GoalModel();
            goal.setTitle(goalRequest.getTitle());
            goal.setDescription(goalRequest.getDescription());
            goal.setFrequency(GoalModel.Frequency.valueOf(goalRequest.getFrequency()));
            goal.setStartDate(goalRequest.getStartDate());
            goal.setTargetDate(goalRequest.getTargetDate());
            goal.setCompletionStatus(goalRequest.isCompletionStatus());
            goal.setOwner(owner);
            goalRepository.save(goal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<GoalModel> getMyGoals(UUID id) {
        try{
            return goalRepository.findByOwnerId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<GoalModel> getScheduledGoals(UUID id) {
        try {
            // Fetch all goals from the repository
            List<GoalModel> allGoals = goalRepository.findAll();

            // Fetch all tracked goals from the repository
            List<GoalTrackerModel> trackedGoals = goalTrackerRepository.findAll();
            Set<UUID> trackedGoalIds = trackedGoals.stream()
                    .filter(goalTracker -> goalTracker.getDate().isEqual(LocalDate.now()))
                    .map(GoalTrackerModel::getGoalId)
                    .collect(Collectors.toSet());

            // Filter goals based on frequency, current date, start date,
            // exclude tracked goals with today's date, and exclude completed goals
            return allGoals.stream()
                    .filter(this::isGoalScheduled)  // Apply scheduling logic
                    .filter(goal -> !trackedGoalIds.contains(goal.getId())) // Exclude tracked goals for today
                    .filter(goal -> !goal.isCompletionStatus()) // Exclude completed goals
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteGoal(UUID id) {
        try {
            Optional<GoalModel> goal = goalRepository.findById(id);
            goal.ifPresent(goalModel -> goalRepository.delete(goalModel));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void markCompleted(UUID id) {
        GoalModel goal = goalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goal not found with ID: " + id));

        goal.setCompletionStatus(true);
        goalRepository.save(goal);
    }
    private boolean isGoalScheduled(GoalModel goal) {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = goal.getStartDate();
        GoalModel.Frequency frequency = goal.getFrequency();

        return switch (frequency) {
            case DAILY -> !currentDate.isBefore(startDate);
            case WEEKLY ->
                    !currentDate.isBefore(startDate) && (currentDate.toEpochDay() - startDate.toEpochDay()) % 7 == 0;
            case MONTHLY ->
                    !currentDate.isBefore(startDate) && currentDate.getDayOfMonth() == startDate.getDayOfMonth();
            case QUARTERLY ->
                    !currentDate.isBefore(startDate) && currentDate.getDayOfMonth() == startDate.getDayOfMonth() &&
                            (currentDate.getMonthValue() - startDate.getMonthValue()) % 3 == 0;
            case HALF_YEARLY ->
                    !currentDate.isBefore(startDate) && currentDate.getDayOfMonth() == startDate.getDayOfMonth() &&
                            (currentDate.getMonthValue() - startDate.getMonthValue()) % 6 == 0;
            case ANNUALLY -> !currentDate.isBefore(startDate) && currentDate.getDayOfYear() == startDate.getDayOfYear();
            default -> false;
        };

}}
