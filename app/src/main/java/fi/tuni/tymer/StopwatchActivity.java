package fi.tuni.tymer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StopwatchActivity extends AppCompatActivity {

    protected boolean ongoing = false;
    protected Thread thread;

    @BindView(R.id.stopwatchStartStop)
    protected Button stopwatchStartStop;
    @BindView(R.id.stopwatchResetLap)
    protected Button stopwatchResetLap;
    @BindView(R.id.mainStopwatch)
    protected TextView mainStopwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.stopwatchStartStop)
    protected void onClickStopwatchStartStop() {
        if (ongoing) {
            stopStopwatch();
        } else {
            starStopwatch();
        }
    }

    @OnClick(R.id.stopwatchResetLap)
    protected void onClickStopwatchResetLap() {
        if (ongoing) {

        } else {
            TextView mainStopwatch = findViewById(R.id.mainStopwatch);
            mainStopwatch.setText("00:00:00");
        }
    }

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
