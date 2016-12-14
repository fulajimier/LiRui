package com.example.lirui.as;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;

import com.example.lirui.R;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by 275073 on 2016/8/12.
 */
public class MyMessageActivity extends SwipeBackActivity {
    private SwipeBackLayout mSwipeBackLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymessage_activity);
        //右滑的东西
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_ALL);
        //设置是否可以滑动
        mSwipeBackLayout.setEnableGesture(true);

        //获取屏幕的宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int  phoneWidth  = dm.widthPixels ;
        //设置侧滑的区域为屏幕宽度的1/3，如果不设置系统默认为50dip
        mSwipeBackLayout.setEdgeSize(phoneWidth / 3);
    }
}
