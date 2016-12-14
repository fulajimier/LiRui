package com.example.lirui.as.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by 275073 on 2016/11/15.
 */
public class BaseActivity extends SwipeBackActivity {
    private SwipeBackLayout mSwipeBackLayout;
    //全局变量
    public static MyApplication application;
    // 等待框
    public ProgressDialog dialogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        application = new MyApplication();
        // threadPool = Executors.newFixedThreadPool(5);
    }

    public void setRoll(Boolean b) {
        //右滑的东西
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_ALL);
        //设置是否可以滑动
        mSwipeBackLayout.setEnableGesture(false);

        //获取屏幕的宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int phoneWidth = dm.widthPixels;
        //设置侧滑的区域为屏幕宽度的1/3，如果不设置系统默认为50dip
        mSwipeBackLayout.setEdgeSize(phoneWidth / 3);
    }

    /**
     * 添加点击空白时隐藏软键盘事件
     *
     * @return
     */
    public boolean onTouchEvent(MotionEvent event) {
     //   InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);


        return super.onTouchEvent(event);
    }

    //启动一个等待框
    public void showDailog(String title, String message) {
        if (dialogs != null) {
            dialogs.setTitle(title);
            dialogs.setMessage(message);
            dialogs.show();
        } else {
            dialogs = ProgressDialog.show(this, title, message);
        }
    }

    //取消一个 等待框
    public void cancelDialog() {
        if (dialogs != null) {
            dialogs.dismiss();
        }
    }
}
