package com.goalfitness;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class DataStore {
    private static final String FILE = "data.json";

    public static void saveData(List<UserProfile> users) {
        JSONArray arr = new JSONArray();
        for (UserProfile u : users) {
            JSONObject obj = new JSONObject();
            obj.put("userId", u.getUserId());
            obj.put("name", u.getName());
            obj.put("gender", u.getGender());
            obj.put("age", u.getAge());
            obj.put("weight", u.getWeight());
            if (u.getGoal() != null) {
                JSONObject g = new JSONObject();
                g.put("goalId", u.getGoal().getGoalId());
                g.put("goalType", u.getGoal().getGoalType());
                g.put("targetValue", u.getGoal().getTargetValue());
                g.put("startDate", u.getGoal().getStartDate());
                g.put("endDate", u.getGoal().getEndDate());
                obj.put("goal", g);
            }
            obj.put("streakCount", u.getStreakCount());

            JSONArray habitsArr = new JSONArray();
            for (Habit h : u.getHabits()) {
                JSONObject hObj = new JSONObject();
                hObj.put("habitId", h.getHabitId());
                hObj.put("habitName", h.getHabitName());
                hObj.put("frequency", h.getFrequency());
                hObj.put("status", h.isCompleted());
                habitsArr.put(hObj);
            }
            obj.put("habits", habitsArr);
            arr.put(obj);
        }
        try (FileWriter fw = new FileWriter(FILE)) {
            fw.write(arr.toString(2));
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static List<UserProfile> loadData() {
        List<UserProfile> users = new ArrayList<>();
        try (FileReader fr = new FileReader(FILE)) {
            JSONArray arr = new JSONArray(new JSONTokener(fr));
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Goal goal = null;
                if (obj.has("goal")) {
                    JSONObject g = obj.getJSONObject("goal");
                    goal = new Goal(g.getInt("goalId"), g.getString("goalType"),
                                    g.getDouble("targetValue"), g.getString("startDate"),
                                    g.getString("endDate"));
                }
                UserProfile u = new UserProfile(
                    obj.getInt("userId"),
                    obj.getString("name"),
                    obj.optString("gender", "N/A"),
                    obj.optInt("age", 0),
                    obj.optDouble("weight", 0.0),
                    goal
                );
                u.setStreakCount(obj.optInt("streakCount", 0));

                JSONArray habitsArr = obj.optJSONArray("habits");
                if (habitsArr != null) {
                    for (int j = 0; j < habitsArr.length(); j++) {
                        JSONObject hObj = habitsArr.getJSONObject(j);
                        Habit h = new Habit(hObj.getInt("habitId"),
                                            hObj.getString("habitName"),
                                            hObj.getString("frequency"));
                        h.setCompleted(hObj.getBoolean("status"));
                        u.addHabit(h);
                    }
                }
                users.add(u);
            }
        } catch (IOException e) {
            System.out.println("No previous data found. Starting fresh.");
        }
        return users;
    }
}
