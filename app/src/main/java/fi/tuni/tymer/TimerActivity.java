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
                    countDownTimer.cancel();

                    timerStartStop.setText("start");

                    ongoing = false;

                    mainTimerHour.setText("00");
                    mainTimerMinute.setText("00");
                    mainTimerSecond.setText("00");
                }
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
            countDownTimer = new CountDownTimer(timerToMilliseconds(), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if ( (int) millisUntilFinished / 360000 != 0) {
                        mainTimerHour.setText((int) millisUntilFinished / 360000);
                    }
                    if ( (int) millisUntilFinished % 360000 / 60000 != 0) {
                        mainTimerMinute.setText((int) millisUntilFinished % 360000 / 60000);
                    }
                    mainTimerSecond.setText( (int) millisUntilFinished % 360000 % 60000 / 1000);
                }

                @Override
                public void onFinish() {
                    ongoing = false;
                }
            }.start();

            ongoing = true;

        } else if (ongoing) {
            countDownTimer.cancel();

            timerStartStop.setText("start");

            ongoing = false;
        }
    }

    protected long timerToMilliseconds() {
        try {
            return (((Integer.parseInt(String.valueOf(mainTimerHour.getText())) * 60) + (Integer.parseInt(String.valueOf(mainTimerMinute.getText()))) * 60) + Integer.parseInt(String.valueOf(mainTimerSecond.getText()))) * 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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
