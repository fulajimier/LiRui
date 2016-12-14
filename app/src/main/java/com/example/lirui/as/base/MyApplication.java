package com.example.lirui.as.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 275073 on 2016/11/15.
 */
//全局变量量类
public class MyApplication extends android.app.Application {
    //线程池
    public static ExecutorService threadPool;
    //判断是不是已经登录
    public static Boolean isLogin;

    @Override
    public void onCreate() {
        super.onCreate();
        //创建一个  最大数目为5的线程池
        threadPool = Executors.newFixedThreadPool(5);
        //默认为未登录
        isLogin = false;
    }
}

