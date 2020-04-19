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

/**
 *Class which holds the Timer functions.
 */
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

    /**
     * Checks if timer has been initialized and if it hasn't, then it initializes the timer.
     */
    protected void initMainTimer() {
        if (!ongoing) {
            initTimerStopStartButton();
        }
    }

    /**
     * Adds long click for timerStartStopButton.
     * Resets timer.
     */
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

    /**
     * If Timer has started, timerStartStop-button will stop it.
     * If Timer hasn't started, timerStartStop-button will start it.
     */
    @OnClick(R.id.timerStartStop)
    public void onClickTimerStartStop() {
        if (!ongoing) {
            timerStartStop.setText("stop");
            setEditText(false);
            countDownTimer = new CountDownTimer(timerToMilliseconds(), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (Integer.parseInt(String.valueOf(mainTimerSecond.getText())) == 0) {
                        mainTimerSecond.setText("59");
                        if (Integer.parseInt(String.valueOf(mainTimerMinute.getText())) == 0) {
                            mainTimerMinute.setText("59");
                            mainTimerHour.setText(Integer.parseInt(String.valueOf(mainTimerHour.getText())) - 1 +"");
                        } else {
                            mainTimerMinute.setText(Integer.parseInt(String.valueOf(mainTimerMinute.getText())) - 1 +"");
                        }
                    } else {
                        mainTimerSecond.setText(Integer.parseInt(String.valueOf(mainTimerSecond.getText())) - 1 +"");
                    }
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

    /**
     * Stops timer
     */
    protected void stopTimer() {
        countDownTimer.cancel();

        timerStartStop.setText("start");

        setEditText(true);

        ongoing = false;
    }

    /**
     * Turns hours, minutes and seconds to milliseconds.
     * @return milliseconds
     */
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

    /**
     * Makes or doesn't make time editable and focusable depending on given boolean.
     *
     * Currently broken
     *
     * @param editable
     */
    protected void setEditText(boolean editable) {
    //    mainTimerHour.setFocusable(editable);
    //    mainTimerHour.setClickable(editable);

    //    mainTimerMinute.setFocusable(editable);
    //    mainTimerMinute.setClickable(editable);

    //    mainTimerSecond.setFocusable(editable);
    //    mainTimerSecond.setClickable(editable);
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
