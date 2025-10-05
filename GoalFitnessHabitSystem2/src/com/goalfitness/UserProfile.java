package com.goalfitness;

import java.util.ArrayList;
import java.util.List;

public class UserProfile {
    private int userId;
    private String name;
    private String gender;
    private int age;
    private double weight;
    private Goal goal; // composition: User has a Goal
    private List<Habit> habits;
    private int streakCount;

    public UserProfile(int userId, String name, String gender, int age, double weight, Goal goal) {
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.goal = goal;
        this.habits = new ArrayList<>();
        this.streakCount = 0;
    }

    // Encapsulation: getters and setters
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public int getAge() { return age; }
    public double getWeight() { return weight; }
    public Goal getGoal() { return goal; }
    public int getStreakCount() { return streakCount; }

    public void setName(String name) { this.name = name; }
    public void setGender(String gender) { this.gender = gender; }
    public void setAge(int age) { this.age = age; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setGoal(Goal goal) { this.goal = goal; }
    public void setStreakCount(int streakCount) { this.streakCount = streakCount; }

    public void addHabit(Habit h) { habits.add(h); }
    public void removeHabit(int habitId) { habits.removeIf(h -> h.getHabitId() == habitId); }
    public List<Habit> getHabits() { return habits; }

    public String getProfile() {
        String g = (goal != null) ? goal.getGoalSummary() : "No goal set";
        return "ID:" + userId + " | " + name + " | " + gender + " | Age:" + age +
               " | Weight:" + weight + "kg | " + g + " | Streak:" + streakCount;
    }
}
