package fi.tuni.tymer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
