package com.frv.workout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class StopWatchFragment extends Fragment implements View.OnClickListener {
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_stop_watch, container, false);
        runTimer(layout);

        Button startB = layout.findViewById(R.id.start_button);
        startB.setOnClickListener(this);
        Button stopB = layout.findViewById(R.id.stop_button);
        stopB.setOnClickListener(this);
        Button resetB = layout.findViewById(R.id.reset_button);
        resetB.setOnClickListener(this);

        return layout;
    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    private void runTimer(View view) {
        final TextView timeView = view.findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int sec = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, sec);

                timeView.setText(time);

                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    } // runTimer()

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        outState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(wasRunning) {
            running = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_button:
                onClickStart(view);
                break;
            case R.id.stop_button:
                onClickStop(view);
                break;
            case R.id.reset_button:
                onClickReset(view);
                break;
        }
    }
}