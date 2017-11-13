package com.golendukhin.countdown;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.joda.time.DateTime;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String format = "%02d:%02d:%02d:%02d";
        DateTime currentTime = new DateTime();
        DateTime newYearTime = new DateTime(2018, 2, 6, 0, 0);
        long interval = newYearTime.getMillis() - currentTime.getMillis();
        final TextView countDownTextView = findViewById(R.id.elapsed_time_text_view);
        TextView titleTextView = findViewById(R.id.title_text_view);

        boolean landScapeOrientation = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (landScapeOrientation) {
            countDownTextView.setTextColor(Color.BLACK);
            titleTextView.setTextColor(Color.BLACK);
        }

        new CountDownTimer(interval, 1000) {
            public void onTick(long millisUntilFinished) {
                int days = (int)(millisUntilFinished / (24 * 60 * 60 * 1000));
                long left = millisUntilFinished % (24 * 60 * 60 * 1000);
                int hours = (int)left / (60 * 60 * 1000);
                left %= 60 * 60 * 1000;
                int minutes = (int)left / (60 * 1000);
                left %= 60 * 1000;
                int seconds = (int)left / 1000;

                countDownTextView.setText("time left" + "\n" + String.format(format, days, hours, minutes, seconds));
            }

            public void onFinish() {}
        }.start();
    }
}