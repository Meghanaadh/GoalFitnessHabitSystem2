package com.goalfitness;

public class Habit {
    private int habitId;
    private String habitName;
    private String frequency;
    private boolean status;

    public Habit(int habitId, String habitName, String frequency) {
        this.habitId = habitId;
        this.habitName = habitName;
        this.frequency = frequency;
        this.status = false;
    }

    // Encapsulation
    public int getHabitId() { return habitId; }
    public String getHabitName() { return habitName; }
    public String getFrequency() { return frequency; }
    public boolean isCompleted() { return status; }
    public void setCompleted(boolean s) { this.status = s; }

    public void markComplete() { status = true; }
    public void resetHabit() { status = false; }

    public String getHabitStatus() {
        return habitId + ". " + habitName + " (" + frequency + ") - " + (status ? "Completed" : "Pending");
    }
}
