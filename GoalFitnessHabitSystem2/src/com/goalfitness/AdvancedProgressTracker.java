package com.goalfitness;

public class AdvancedProgressTracker extends ProgressTracker {

    public AdvancedProgressTracker(int progressId, int userId, int initialStreak) {
        super(progressId, userId, initialStreak);
    }

    @Override
    public String generateSummary() {
        if (streakCount >= 30) {
            return "🏆 Legendary! " + streakCount + " day streak — unbelievable consistency!";
        } else if (streakCount >= 7) {
            return "🔥 Great job! " + streakCount + " day streak — keep it up!";
        } else if (streakCount >= 3) {
            return "💪 Nice! " + streakCount + " day streak — you're building momentum.";
        } else {
            return super.generateSummary();
        }
    }
}
