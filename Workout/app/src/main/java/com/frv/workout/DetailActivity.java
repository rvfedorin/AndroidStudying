package com.frv.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {
    public static final String WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        WorkoutDetailFragment detailFragment = (WorkoutDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        detailFragment.setWorkoutId((Long) getIntent().getExtras().get(WORKOUT_ID));
    }
}