package fi.tuni.tymer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.toClockFromAlarm)
    public void toClockFromAlarm() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.toTimerFromAlarm)
    public void toTimerFromClock() {
        Intent i = new Intent(this, TimerActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.toStopwatchFromAlarm)
    public void toStopwatchFromClock() {
        Intent i = new Intent(this, StopwatchActivity.class);
        startActivity(i);
    }
}
