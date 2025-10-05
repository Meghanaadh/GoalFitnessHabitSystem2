package com.goalfitness;

public class Goal {
    private int goalId;
    private String goalType;
    private double targetValue;
    private String startDate;
    private String endDate;

    public Goal(int goalId, String goalType, double targetValue, String startDate, String endDate) {
        this.goalId = goalId;
        this.goalType = goalType;
        this.targetValue = targetValue;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getGoalId() { return goalId; }
    public String getGoalType() { return goalType; }
    public double getTargetValue() { return targetValue; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }

    public String getGoalSummary() {
        return "Goal[" + goalId + "]: " + goalType + " target=" + targetValue + " (" + startDate + " to " + endDate + ")";
    }
}
