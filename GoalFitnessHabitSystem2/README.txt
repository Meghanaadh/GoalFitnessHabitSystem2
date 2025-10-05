GoalFitnessHabitSystem_OOPEnhanced
----------------------------------

This project includes explicit OOP concepts:
- Encapsulation (private fields + getters/setters)
- Inheritance (AdvancedProgressTracker extends ProgressTracker)
- Polymorphism (method overriding & overloading, BaseTracker reference)
- Association/Composition (User has Habits and a Goal)
- Abstraction (BaseTracker abstract class)

Files are in src/com/goalfitness/

Requirements: add org.json jar to lib/ (e.g. json-20230227.jar)

How to compile and run (Linux/Mac):
    javac -cp lib/json-20230227.jar -d out src/com/goalfitness/*.java
    java -cp out:lib/json-20230227.jar com.goalfitness.MainApp

Windows (use ; as classpath separator):
    javac -cp lib\json-20230227.jar -d out src\com\goalfitness\*.java
    java -cp out;lib\json-20230227.jar com.goalfitness.MainApp

A sample data.json is included to show demo user on first run.
