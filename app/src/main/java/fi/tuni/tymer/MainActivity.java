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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        startClock();
    }

    public void startClock() {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                while (1 > 0) {
                    try {
                        Thread.sleep(10);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView mainClock = findViewById(R.id.mainClock);
                                mainClock.setText((String) new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (NullPointerException r) {
                        r.printStackTrace();
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
