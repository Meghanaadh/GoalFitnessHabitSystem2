package com.goalfitness;

public abstract class BaseTracker {
    protected int progressId;
    protected int userId;
    protected int streakCount;

    public BaseTracker(int progressId, int userId, int initialStreak) {
        this.progressId = progressId;
        this.userId = userId;
        this.streakCount = initialStreak;
    }

    public abstract void updateProgress(boolean habitDone);
    public abstract String generateSummary();

    public int getStreakCount() { return streakCount; }
}
