package fi.tuni.tymer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StopwatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        ButterKnife.bind(this);
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
