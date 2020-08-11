package com.frv.workout;

public class Workout {
    private String name;
    private String descritpion;

    public static final Workout[] workouts = {
            new Workout("The Limp Loosiner", " 5 handstand push ups\n10 1-legged squats\n15 Pull ups"),
            new Workout("Core Agony", "100 Pull-ups\n100 Push-ups\n100 Squats"),
            new Workout("The Wimp Special", "5 Pull-ups\n10 Push-ups\n15 Squats"),
            new Workout("Strength and Length", "500 meters run\n21 x 1.5 pood kettleball swing\n12 x pull-ups")
    };

    public Workout(String name, String descritpion) {
        this.name = name;
        this.descritpion = descritpion;
    }

    public String getName() {
        return name;
    }

    public String getDescritpion() {
        return descritpion;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
