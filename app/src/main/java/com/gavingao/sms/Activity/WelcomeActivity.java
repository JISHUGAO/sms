package com.gavingao.sms.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.gavingao.sms.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public class WelcomeActivity extends BaseActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        };
        final TextView skip = (TextView)findViewById(R.id.welcome_skip);
        final int time = 1000 * 3;
      
        CountDownTimer timer2 = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String tips = skip.getText().toString();
                int t = time/1000;
                if ("跳转".equals(tips)) {
                    skip.setText("跳转"+t+"秒");
                } else {
                    skip.setText("跳转"+ (t==0? "": (t-1)+"秒"));
                }
            }
            @Override
            public void onFinish() {
                //re.setClickable(true);
                //tv.setText("获取验证码");
            }
        };

        timer2.start();
        timer.schedule(task, time);

    }


}
