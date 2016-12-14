package com.example.lirui.as.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lirui.R;
import com.example.lirui.as.MainActivity;
import com.example.lirui.as.base.BaseActivity;
import com.example.lirui.as.base.MyApplication;

/**
 * Created by 275073 on 2016/11/15.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    //登录按钮
    Button loginButton;
    //跳过登录
    TextView noLogin;
    //用户名
    TextView tv_name;
    //密码
    TextView tv_password;
    //登录的实体类
    LoginEntity entity;
    public static String TAG = "LoginActivity";
    //友盟登录

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(LoginActivity.this, "密码错误，请重试", Toast.LENGTH_LONG).show();
            tv_password.setText(null);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        loginButton = (Button) findViewById(R.id.bt);
        noLogin = (TextView) findViewById(R.id.no_login);
        tv_name = (TextView) findViewById(R.id.name);
        tv_password = (TextView) findViewById(R.id.password);
        //设置监听
        findViewById(R.id.ly).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return false;
            }
        });
        loginButton.setOnClickListener(this);
        noLogin.setOnClickListener(this);
    }
    //登录事件
    public void login() {
        if (tv_name.getText().toString() == null || tv_name.getText().toString().trim().length() == 0 || tv_password.getText().toString() == null || tv_password.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        entity = new LoginEntity();
        entity.setName(tv_name.getText().toString().trim());
        entity.setPassWord(tv_password.getText().toString().trim());
        showDailog("稍等", "登录中...");
        //启动一个线程池
        application.threadPool.execute(new Runnable() {
            @Override
            public void run() {
                //登录操作
                try {
                    Log.i(TAG, "进入登录线程");
                    //模拟登录耗时操作
                    Thread.sleep(2000);
                    if (entity.getName().equals("lirui") && entity.getPassWord().equals("123456")) {
                        //登录成功,改变判断的状态
                        MyApplication.isLogin = true;
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        handler.sendEmptyMessage(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        cancelDialog();
    }

    //跳过登录事件
    public void noLogin() {
        MyApplication.isLogin = false;
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
                login();
                break;
            case R.id.no_login:
                noLogin();
                break;
            default:
                break;
        }
    }
}
