package com.goalfitness;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static List<UserProfile> users = new ArrayList<>();
    private static int userCounter = 1;

    public static void main(String[] args) {
        users = DataStore.loadData();
        if (!users.isEmpty()) userCounter = users.size() + 1;

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== Goal Fitness & Habit Management (OOP Enhanced) =====");
            System.out.println("1. Create User Profile");
            System.out.println("2. View Users");
            System.out.println("3. Add Habit to User");
            System.out.println("4. View User Habits");
            System.out.println("5. Mark Habit Complete");
            System.out.println("6. View Progress (streaks)");
            System.out.println("7. Get Recommendations");
            System.out.println("8. Set / Update Goal for User");
            System.out.println("9. View Goal Details");
            System.out.println("10. Exit");
            System.out.print("Choose an option: " );

            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number."); continue;
            }

            switch (choice) {
                case 1 -> createUser(sc);
                case 2 -> viewUsers();
                case 3 -> addHabitToUser(sc);
                case 4 -> viewUserHabits(sc);
                case 5 -> markHabitComplete(sc);
                case 6 -> viewProgress(sc);
                case 7 -> getRecommendations(sc);
                case 8 -> setOrUpdateGoal(sc);
                case 9 -> viewGoalDetails(sc);
                case 10 -> { DataStore.saveData(users); running = false; }
                default -> System.out.println("Invalid choice.");
            }
        }
        sc.close();
        System.out.println("Goodbye!"); 
    }

    private static void createUser(Scanner sc) {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Enter weight (kg): ");
        double weight = Double.parseDouble(sc.nextLine());
        System.out.print("Enter goal type (weight loss/muscle gain/cardiovascular health): ");
        String goalType = sc.nextLine();
        System.out.print("Enter goal target value (e.g., target weight or metric): ");
        double target = Double.parseDouble(sc.nextLine());
        System.out.print("Enter goal start date (YYYY-MM-DD): ");
        String start = sc.nextLine();
        System.out.print("Enter goal end date (YYYY-MM-DD): ");
        String end = sc.nextLine();

        Goal g = new Goal(1, goalType, target, start, end);
        UserProfile u = new UserProfile(userCounter++, name, gender, age, weight, g);
        users.add(u);
        DataStore.saveData(users);
        System.out.println("✅ User profile (with goal) created! ID=" + u.getUserId());
    }

    private static void viewUsers() {
        if (users.isEmpty()) System.out.println("No users."); 
        for (UserProfile u : users) System.out.println(u.getProfile());
    }

    private static UserProfile findUser(int id) {
        return users.stream().filter(u -> u.getUserId() == id).findFirst().orElse(null);
    }

    private static void addHabitToUser(Scanner sc) {
        System.out.print("Enter User ID: ");
        int id = Integer.parseInt(sc.nextLine());
        UserProfile u = findUser(id); if (u == null) { System.out.println("User not found."); return; }
        System.out.print("Habit Name: "); String name = sc.nextLine();
        System.out.print("Frequency (Daily/Weekly): "); String freq = sc.nextLine();
        int nextId = u.getHabits().size() + 1;
        u.addHabit(new Habit(nextId, name, freq));
        DataStore.saveData(users);
        System.out.println("Habit added."); 
    }

    private static void viewUserHabits(Scanner sc) {
        System.out.print("Enter User ID: ");
        int id = Integer.parseInt(sc.nextLine());
        UserProfile u = findUser(id); if (u == null) { System.out.println("User not found."); return; }
        if (u.getHabits().isEmpty()) { System.out.println("No habits."); return; }
        for (Habit h : u.getHabits()) System.out.println(h.getHabitStatus());
    }

    private static void markHabitComplete(Scanner sc) {
        System.out.print("Enter User ID: ");
        int id = Integer.parseInt(sc.nextLine());
        UserProfile u = findUser(id); if (u == null) { System.out.println("User not found."); return; }
        if (u.getHabits().isEmpty()) { System.out.println("No habits to mark."); return; }
        System.out.println("Select habit to mark complete:"); 
        for (Habit h : u.getHabits()) System.out.println(h.getHabitStatus());
        System.out.print("Enter habit ID: ");
        int hid = Integer.parseInt(sc.nextLine());
        for (Habit h : u.getHabits()) {
            if (h.getHabitId() == hid) {
                if (!h.isCompleted()) {
                    h.markComplete();
                    u.setStreakCount(u.getStreakCount() + 1);
                    System.out.println("✅ Marked as complete: " + h.getHabitName());
                } else {
                    System.out.println("Already completed."); 
                }
                DataStore.saveData(users);
                return;
            }
        }
        System.out.println("Habit not found."); 
    }

    private static void viewProgress(Scanner sc) {
        System.out.print("Enter User ID: ");
        int id = Integer.parseInt(sc.nextLine());
        UserProfile u = findUser(id); if (u == null) { System.out.println("User not found."); return; }
        // polymorphism: use BaseTracker reference
        BaseTracker tracker = new AdvancedProgressTracker(1, u.getUserId(), u.getStreakCount());
        System.out.println(tracker.generateSummary());
        if (tracker instanceof ProgressTracker) ((ProgressTracker)tracker).displayProgress();
    }

    private static void getRecommendations(Scanner sc) {
        System.out.print("Enter User ID: ");
        int id = Integer.parseInt(sc.nextLine());
        UserProfile u = findUser(id); if (u == null) { System.out.println("User not found."); return; }
        RecommendationEngine re = new RecommendationEngine();
        System.out.println(re.generateRecommendations(u.getGoal() != null ? u.getGoal().getGoalType() : u.getGoal().toString()));
    }

    private static void setOrUpdateGoal(Scanner sc) {
        System.out.print("Enter User ID: ");
        int id = Integer.parseInt(sc.nextLine());
        UserProfile u = findUser(id); if (u == null) { System.out.println("User not found."); return; }
        System.out.print("Enter goal type: "); String gt = sc.nextLine();
        System.out.print("Enter target value: "); double tv = Double.parseDouble(sc.nextLine());
        System.out.print("Enter start date: "); String sd = sc.nextLine();
        System.out.print("Enter end date: "); String ed = sc.nextLine();
        Goal g = new Goal(1, gt, tv, sd, ed);
        u.setGoal(g);
        DataStore.saveData(users);
        System.out.println("Goal set/updated."); 
    }

    private static void viewGoalDetails(Scanner sc) {
        System.out.print("Enter User ID: ");
        int id = Integer.parseInt(sc.nextLine());
        UserProfile u = findUser(id); if (u == null) { System.out.println("User not found."); return; }
        if (u.getGoal() == null) { System.out.println("No goal set."); return; }
        System.out.println(u.getGoal().getGoalSummary());
    }
}
