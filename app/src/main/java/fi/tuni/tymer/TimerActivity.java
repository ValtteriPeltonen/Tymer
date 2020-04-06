package fi.tuni.tymer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimerActivity extends AppCompatActivity {

    @BindView(R.id.timerStartStop)
    Button timerStartStop;
    @BindView(R.id.mainTimerHour)
    EditText mainTimerHour;
    @BindView(R.id.mainTimerMinute)
    EditText mainTimerMinute;
    @BindView(R.id.mainTimerSecond)
    EditText mainTimerSecond;
    private boolean ongoing = false;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        ButterKnife.bind(this);
        initMainTimer();
    }

    protected void initMainTimer() {
        if (!ongoing) {
            initTimerStopStartButton();
        }
    }

    protected void initTimerStopStartButton() {
        timerStartStop.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (ongoing) {
                    stopTimer();
                }
                mainTimerHour.setText("HH");
                mainTimerMinute.setText("MM");
                mainTimerSecond.setText("SS");
                return true;
            }
        });

        if (ongoing) {
            timerStartStop.setText("stop");
        }
    }

    @OnClick(R.id.timerStartStop)
    public void onClickTimerStartStop() {
        if (!ongoing) {
            timerStartStop.setText("stop");
            setEditText(false);
            countDownTimer = new CountDownTimer(timerToMilliseconds(), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if ( (int) millisUntilFinished / 360000 != 0) {
                        mainTimerHour.setText((int) (millisUntilFinished / 360000) % 24 + "");
                    }
                    if ( (int) millisUntilFinished % 360000 / 60000 != 0) {
                        mainTimerMinute.setText((int) (millisUntilFinished / 60000) % 60 + "");
                    }
                    mainTimerSecond.setText( (int) (millisUntilFinished / 1000) % 60 + "");
                }

                @Override
                public void onFinish() {
                    stopTimer();
                    mainTimerHour.setText("HH");
                    mainTimerMinute.setText("MM");
                    mainTimerSecond.setText("SS");
                }
            }.start();

            ongoing = true;

        } else if (ongoing) {
            stopTimer();
        }
    }

    protected void stopTimer() {
        countDownTimer.cancel();

        timerStartStop.setText("start");

        setEditText(true);

        ongoing = false;
    }

    protected long timerToMilliseconds() {
        long millis = 0;
        try {
            millis += Long.parseLong(String.valueOf(mainTimerHour.getText())) * 360000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            millis += Long.parseLong(String.valueOf(mainTimerMinute.getText())) * 60000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            millis += Long.parseLong(String.valueOf(mainTimerSecond.getText())) * 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return millis;
    }

    protected void setEditText(boolean editable) {
        mainTimerHour.setFocusable(editable);
        mainTimerHour.setClickable(editable);

        mainTimerMinute.setFocusable(editable);
        mainTimerMinute.setClickable(editable);

        mainTimerSecond.setFocusable(editable);
        mainTimerSecond.setClickable(editable);
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
