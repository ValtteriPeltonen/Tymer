package fi.tuni.tymer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Class which holds the alarm function
 */
@SuppressLint("SetTextI18n")
public class AlarmActivity extends AppCompatActivity {


    @BindView(R.id.alarmText1)
    EditText alarmText1;
    @BindView(R.id.alarmText2)
    EditText alarmText2;
    @BindView(R.id.alarmText3)
    EditText alarmText3;
    @BindView(R.id.alarmText4)
    EditText alarmText4;
    @BindView(R.id.alarmText5)
    EditText alarmText5;
    @BindView(R.id.alarmText6)
    EditText alarmText6;
    @BindView(R.id.alarmText7)
    EditText alarmText7;
    @BindView(R.id.alarmText8)
    EditText alarmText8;
    @BindView(R.id.alarmText9)
    EditText alarmText9;
    @BindView(R.id.alarmText10)
    EditText alarmText10;
    @BindView(R.id.alarmText11)
    EditText alarmText11;
    @BindView(R.id.alarmText12)
    EditText alarmText12;
    @BindView(R.id.alarmText13)
    EditText alarmText13;
    @BindView(R.id.alarmText14)
    EditText alarmText14;

    @BindView(R.id.alarmButton1)
    Button alarmButton1;
    @BindView(R.id.alarmButton2)
    Button alarmButton2;
    @BindView(R.id.alarmButton3)
    Button alarmButton3;
    @BindView(R.id.alarmButton4)
    Button alarmButton4;
    @BindView(R.id.alarmButton5)
    Button alarmButton5;
    @BindView(R.id.alarmButton6)
    Button alarmButton6;
    @BindView(R.id.alarmButton7)
    Button alarmButton7;
    @BindView(R.id.alarmButton8)
    Button alarmButton8;
    @BindView(R.id.alarmButton9)
    Button alarmButton9;
    @BindView(R.id.alarmButton10)
    Button alarmButton10;
    @BindView(R.id.alarmButton11)
    Button alarmButton11;
    @BindView(R.id.alarmButton12)
    Button alarmButton12;
    @BindView(R.id.alarmButton13)
    Button alarmButton13;
    @BindView(R.id.alarmButton14)
    Button alarmButton14;

    private Boolean alarmOn1;
    private Boolean alarmOn2;
    private Boolean alarmOn3;
    private Boolean alarmOn4;
    private Boolean alarmOn5;
    private Boolean alarmOn6;
    private Boolean alarmOn7;
    private Boolean alarmOn8;
    private Boolean alarmOn9;
    private Boolean alarmOn10;
    private Boolean alarmOn11;
    private Boolean alarmOn12;
    private Boolean alarmOn13;
    private Boolean alarmOn14;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);
        startClock();

    }

    /**
     * Starts a background clock function which updates every 5 seconds
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startClock() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    runOnUiThread(() -> {
                        String currentTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                        String[] time = currentTime.split(":");
                        if (time[1].equals("00")) {
                            isMinute(time);
                        }
                    });
                } catch (InterruptedException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Goes through every alarm checking if they are on, and if the hour and minutes match
     *
     * @param time
     */
    protected void isMinute(String[] time) {
        for (int i = 1; i < 15; i++) {
            if (isOn(i)) {
                if (checkTime(0, i, time)) {
                    if (checkTime(1, i, time)) {
                        alarmStart();
                    }
                }
            }
        }
    }

    /**
     * Checks if timeType of the timer of the given alarm matches the currentTime
     *
     * @param timeType
     * @param timerNumber
     * @param currentTime
     * @return
     */
    protected boolean checkTime(int timeType, int timerNumber, String[] currentTime) {
        boolean alarm = false;
        String[] alarmTimerString;
        try {
            switch (timerNumber) {
                case 1:
                    alarmTimerString = alarmText1.getText().toString().split(":");
                    break;
                case 2:
                    alarmTimerString = alarmText2.getText().toString().split(":");
                    break;
                case 3:
                    alarmTimerString = alarmText3.getText().toString().split(":");
                    break;
                case 4:
                    alarmTimerString = alarmText4.getText().toString().split(":");
                    break;
                case 5:
                    alarmTimerString = alarmText5.getText().toString().split(":");
                    break;
                    case 6:
                    alarmTimerString = alarmText6.getText().toString().split(":");
                    break;
                case 7:
                    alarmTimerString = alarmText7.getText().toString().split(":");
                    break;
                    case 8:
                    alarmTimerString = alarmText8.getText().toString().split(":");
                    break;
                case 9:
                    alarmTimerString = alarmText9.getText().toString().split(":");
                    break;
                case 10:
                    alarmTimerString = alarmText10.getText().toString().split(":");
                    break;
                case 11:
                    alarmTimerString = alarmText11.getText().toString().split(":");
                    break;
                case 12:
                    alarmTimerString = alarmText12.getText().toString().split(":");
                    break;
                case 13:
                    alarmTimerString = alarmText13.getText().toString().split(":");
                    break;
                case 14:
                    alarmTimerString = alarmText14.getText().toString().split(":");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + timerNumber);
            }
            if (alarmTimerString[timeType].equals(currentTime[timeType])) {
                alarm = true;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return alarm;
    }

    /**
     * Checks if given alarm is on
     *
     * @param timerNumber
     * @return
     */
    private boolean isOn(int timerNumber) {
        Boolean alarm = false;
        switch (timerNumber) {
            case 1:
                alarm = alarmOn1;
                break;
            case 2:
                alarm = alarmOn2;
                break;
            case 3:
                alarm = alarmOn3;
                break;
            case 4:
                alarm = alarmOn4;
                break;
            case 5:
                alarm = alarmOn5;
                break;
            case 6:
                alarm = alarmOn6;
                break;
            case 7:
                alarm = alarmOn7;
                break;
            case 8:
                alarm = alarmOn8;
                break;
            case 9:
                alarm = alarmOn9;
                break;
            case 10:
                alarm = alarmOn10;
                break;
            case 11:
                alarm = alarmOn11;
                break;
            case 12:
                alarm = alarmOn12;
                break;
            case 13:
                alarm = alarmOn13;
                break;
            case 14:
                alarm = alarmOn14;
                break;
        }
        return alarm;
    }

    @OnClick(R.id.alarmButton1)
    public void setAlarmButton1() {
        if (alarmButton1.getText().toString().equals("Set on")) {
            alarmButton1.setText("Set off");
            alarmOn1 = true;
        } else {
            alarmButton1.setText("Set on");
            alarmOn1 = false;
        }
    }

    @OnClick(R.id.alarmButton2)
    public void setAlarmButton2() {
        if (alarmButton2.getText().toString().equals("Set on")) {
            alarmButton2.setText("Set off");
            alarmOn2 = true;
        } else {
            alarmButton2.setText("Set on");
            alarmOn2 = false;
        }
    }

    @OnClick(R.id.alarmButton3)
    public void setAlarmButton3() {
        if (alarmButton3.getText().toString().equals("Set on")) {
            alarmButton3.setText("Set off");
            alarmOn3 = true;
        } else {
            alarmButton3.setText("Set on");
            alarmOn3 = false;
        }
    }
    @OnClick(R.id.alarmButton4)
    public void setAlarmButton4() {
        if (alarmButton4.getText().toString().equals("Set on")) {
            alarmButton4.setText("Set off");
            alarmOn4 = true;
        } else {
            alarmButton4.setText("Set on");
            alarmOn4 = false;
        }
    }
    @OnClick(R.id.alarmButton5)
    public void setAlarmButton5() {
        if (alarmButton5.getText().toString().equals("Set on")) {
            alarmButton5.setText("Set off");
            alarmOn5 = true;
        } else {
            alarmButton5.setText("Set on");
            alarmOn5 = false;
        }
    }

    @OnClick(R.id.alarmButton6)
    public void setAlarmButton6() {
        if (alarmButton6.getText().toString().equals("Set on")) {
            alarmButton6.setText("Set off");
            alarmOn6 = true;
        } else {
            alarmButton6.setText("Set on");
            alarmOn6 = false;
        }
    }
    @OnClick(R.id.alarmButton7)
    public void setAlarmButton7() {
        if (alarmButton7.getText().toString().equals("Set on")) {
            alarmButton7.setText("Set off");
            alarmOn7 = true;
        } else {
            alarmButton7.setText("Set on");
            alarmOn7 = false;
        }
    }
    @OnClick(R.id.alarmButton8)
    public void setAlarmButton8() {
        if (alarmButton8.getText().toString().equals("Set on")) {
            alarmButton8.setText("Set off");
            alarmOn8 = true;
        } else {
            alarmButton8.setText("Set on");
            alarmOn8 = false;
        }
    }
    @OnClick(R.id.alarmButton9)
    public void setAlarmButton9() {
        if (alarmButton9.getText().toString().equals("Set on")) {
            alarmButton9.setText("Set off");
            alarmOn9 = true;
        } else {
            alarmButton9.setText("Set on");
            alarmOn9 = false;
        }
    }
    @OnClick(R.id.alarmButton10)
    public void setAlarmButton10() {
        if (alarmButton10.getText().toString().equals("Set on")) {
            alarmButton10.setText("Set off");
            alarmOn10 = true;
        } else {
            alarmButton10.setText("Set on");
            alarmOn10 = false;
        }
    }
    @OnClick(R.id.alarmButton11)
    public void setAlarmButton11() {
        if (alarmButton11.getText().toString().equals("Set on")) {
            alarmButton11.setText("Set off");
            alarmOn11 = true;
        } else {
            alarmButton11.setText("Set on");
            alarmOn11 = false;
        }
    }
    @OnClick(R.id.alarmButton12)
    public void setAlarmButton12() {
        if (alarmButton12.getText().toString().equals("Set on")) {
            alarmButton12.setText("Set off");
            alarmOn12 = true;
        } else {
            alarmButton12.setText("Set on");
            alarmOn12 = false;
        }
    }
    @OnClick(R.id.alarmButton13)
    public void setAlarmButton13() {
        if (alarmButton13.getText().toString().equals("Set on")) {
            alarmButton13.setText("Set off");
            alarmOn13 = true;
        } else {
            alarmButton13.setText("Set on");
            alarmOn13 = false;
        }
    }
    @OnClick(R.id.alarmButton14)
    public void setAlarmButton14() {
        if (alarmButton14.getText().toString().equals("Set on")) {
            alarmButton14.setText("Set off");
            alarmOn14 = true;
        } else {
            alarmButton14.setText("Set on");
            alarmOn14 = false;
        }
    }

    /**
     * Plays alarm sound
     */
    protected void alarmStart() {
        System.out.println("Alarm Start");
        MediaPlayer.create(this, R.raw.alarm).start();
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
