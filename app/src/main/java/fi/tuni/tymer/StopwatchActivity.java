package fi.tuni.tymer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Class which holds Stopwatch functions.
 */
public class StopwatchActivity extends AppCompatActivity {

    protected boolean ongoing = false;
    protected Thread thread;
    protected ArrayList<String> lapList = new ArrayList<>();
    protected int laps = 0;

    @BindView(R.id.stopwatchStartStop)
    protected Button stopwatchStartStop;
    @BindView(R.id.stopwatchResetLap)
    protected Button stopwatchResetLap;
    @BindView(R.id.mainStopwatch)
    protected TextView mainStopwatch;
    @BindView(R.id.lap1)
    protected TextView lap1;
    @BindView(R.id.lap2)
    protected TextView lap2;
    @BindView(R.id.lap3)
    protected TextView lap3;
    @BindView(R.id.lap4)
    protected TextView lap4;
    @BindView(R.id.lap5)
    protected TextView lap5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        ButterKnife.bind(this);
    }

    /**
     * Checks if stopwatch is currently running or not.
     */
    @OnClick(R.id.stopwatchStartStop)
    protected void onClickStopwatchStartStop() {
        if (ongoing) {
            stopStopwatch();
        } else {
            starStopwatch();
        }
    }

    /**
     * Resets the stopwatch if it isn't running.
     * Sets a lap if stopwatch is running.
     */
    @OnClick(R.id.stopwatchResetLap)
    protected void onClickStopwatchResetLap() {
        if (ongoing) {
            laps += 1;
            if (laps == 1) {
                lap1.setText(mainStopwatch.getText());
            }
            if (laps == 2) {
                lap2.setText(mainStopwatch.getText());
            }
            if (laps == 3) {
                lap3.setText(mainStopwatch.getText());
            }
            if (laps == 4) {
                lap4.setText(mainStopwatch.getText());
            }
            if (laps == 5) {
                lap5.setText(mainStopwatch.getText());
            }
        } else {
            TextView mainStopwatch = findViewById(R.id.mainStopwatch);
            laps = 0;
            mainStopwatch.setText("00:00:00");
            lap1.setText("");
            lap2.setText("");
            lap3.setText("");
            lap4.setText("");
            lap5.setText("");
        }
    }

    /**
     * Starts stopwatch and a thread which updates the stopwatch every 1000 milliseconds.
     */
    protected void starStopwatch() {
        ongoing = true;

        stopwatchResetLap.setText("Lap");
        stopwatchStartStop.setText("Stop");

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int seconds = 0;
                int minutes = 0;
                int hours = 0;

                if (!mainStopwatch.getText().equals("00:00:00")) {
                    String[] time = mainStopwatch.getText().toString().split(":");
                    seconds = Integer.parseInt(time[2]);
                    minutes = Integer.parseInt(time[1]);
                    hours = Integer.parseInt(time[0]);
                }
                while (ongoing) {
                    try {
                        Thread.sleep(1000);
                        if (seconds == 59) {
                            if ( minutes == 59) {
                                minutes = 0;
                                hours += 1;
                            } else {
                                minutes += 1;
                            }
                            seconds = 0;
                        } else {
                            seconds += 1;
                        }
                        if (ongoing) {
                            int finalHours = hours;
                            int finalMinutes = minutes;
                            int finalSeconds = seconds;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mainStopwatch.setText(String.format("%02d:%02d:%02d", finalHours, finalMinutes, finalSeconds));
                                }
                            });
                        }
                    } catch (InterruptedException | NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }

    /**
     * Stops stopwatch.
     */
    protected void stopStopwatch() {
        ongoing = false;

        stopwatchResetLap.setText("Reset");
        stopwatchStartStop.setText("Start");
    }

    @OnClick(R.id.toClockFromStopwatch)
    public void toClockFromStopwatch() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.toAlarmFromStopwatch)
    public void toAlarmFromStopwatch() {
        Intent i = new Intent(this, AlarmActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.toTimerFromStopwatch)
    public void toTimerFromStopwatch() {
        Intent i = new Intent(this, TimerActivity.class);
        startActivity(i);
    }
}
