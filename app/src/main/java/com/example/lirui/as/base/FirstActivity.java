package com.example.lirui.as.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lirui.R;
import com.example.lirui.as.login.LoginActivity;

/**
 * Created by 275073 on 2016/11/15.
 */
public class FirstActivity extends Activity {
    public static String TAG = "myLog:";
    //用来记录当前的倒计时，i+2
    int time = 5;
    ImageView img;
    Button button;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    //为
                    //每次进来一次就说明时间过了1s，就减1
                    time--;
                    //如果为0的话，说明定时炸弹需要爆炸了
                    if (time == 0) {
                        startActivity(new Intent(FirstActivity.this, LoginActivity.class));
                        finish();
                    } else if (time == 1) {
                        //这个 是让按钮可以点击
                        button.setText("跳过");
                        button.setEnabled(true);
                    } else {
                        button.setText(time-1 + "s后跳过");
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.first_activity);
        img = (ImageView) findViewById(R.id.first_image);
        img.setBackgroundResource(R.drawable.welcome);
        button = (Button) findViewById(R.id.tv_time);
        //将按钮设置为不可按
        button.setEnabled(false);
        //跳过 操作
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, LoginActivity.class));
                finish();
            }
        });
        //模拟 程序开始的时候 进行的后台操作
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    super.run();
                }
            }
        }.start();
    }
}
