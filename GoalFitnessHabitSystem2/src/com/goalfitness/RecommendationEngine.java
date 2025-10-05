package com.goalfitness;

public class RecommendationEngine implements Recommendable {
    @Override
    public String generateRecommendations(String goalType) {
        if (goalType == null) return "General: Stay active and maintain a balanced diet.";
        switch(goalType.toLowerCase()) {
            case "weight loss": return "30 min cardio + calorie deficit + daily walks.";
            case "muscle gain": return "Strength training 4-5x/week + higher protein intake.";
            case "cardiovascular health": return "20-30 min moderate cardio most days + yoga.";
            default: return "General: Stay consistent and active.";
        }
    }

    // Method overloading -> polymorphism
    public String generateRecommendations(String goalType, int intensityLevel) {
        return generateRecommendations(goalType) + " Intensity level: " + intensityLevel;
    }
}
