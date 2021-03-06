package fi.tuni.tymer;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main class which holds a clock, which updates itself every 10 milliseconds.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        startClock();
    }

    /**
     * Starts new the runnable which gets time from Android calendar class and formats it to the wanted form every 10 milliseconds
     */
    public void startClock() {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView mainClock = findViewById(R.id.mainClock);
                                String currentTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                                mainClock.setText(currentTime);
                            }
                        });
                    } catch (InterruptedException | NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @OnClick(R.id.toAlarmFromClock)
    public void toAlarmFromClock() {
        Intent i = new Intent(this, AlarmActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.toTimerFromClock)
    public void toTimerFromClock() {
        Intent i = new Intent(this, TimerActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.toStopwatchFromClock)
    public void toStopwatchFromClock() {
        Intent i = new Intent(this, StopwatchActivity.class);
        startActivity(i);
    }
}
