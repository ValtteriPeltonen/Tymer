package fi.tuni.tymer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.toClockFromTimer)
    public void toClockFromTimer() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.toAlarmFromTimer)
    public void toAlarmFromTimer() {
        Intent i = new Intent(this, AlarmActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.toStopwatchFromTimer)
    public void toStopwatchFromTimer() {
        Intent i = new Intent(this, StopwatchActivity.class);
        startActivity(i);
    }
}
