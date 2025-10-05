package com.goalfitness;

public class ProgressTracker extends BaseTracker {

    public ProgressTracker(int progressId, int userId, int initialStreak) {
        super(progressId, userId, initialStreak);
    }

    @Override
    public void updateProgress(boolean habitDone) {
        if (habitDone) streakCount++;
        else streakCount = 0;
    }

    @Override
    public String generateSummary() {
        return "User ID: " + userId + " | Current Streak: " + streakCount + " day(s)";
    }

    public void displayProgress() {
        System.out.print("Progress: [");
        for (int i = 0; i < streakCount; i++) System.out.print("#");
        System.out.println("] " + streakCount + " day(s)");
    }
}
