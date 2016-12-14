package com.example.lirui.as;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.lirui.R;
import com.example.lirui.as.fragment.FirstFragment;
import com.example.lirui.as.fragment.GrilFragment;
import com.example.lirui.as.fragment.LoveFragment;
import com.example.lirui.as.fragment.MoreFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    //当前的界面
    int i = 1;
    //滑动的管理
    private GestureDetector gestureDetector;
    //管理Fragment的
    FragmentManager manager;
    //动作的
    private FragmentTransaction transaction;
    //点击的控件
    TextView first, love, girl, more;
    //Fragment布局
    Fragment firstFragment, loveFragment, grilFragment, moreFragment, baseFragment;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);
        //初始化的时候 把首页当成是最开始的页面
        baseFragment = new FirstFragment();
        //初始化 Fragment的一些事务的
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.content_layout, baseFragment).commit();
        first = (TextView) findViewById(R.id.tv_fisrt);
        love = (TextView) findViewById(R.id.tv_love);
        girl = (TextView) findViewById(R.id.tv_gril);
        more = (TextView) findViewById(R.id.tv_more);
        first.setOnClickListener(this);
        love.setOnClickListener(this);
        girl.setOnClickListener(this);
        more.setOnClickListener(this);
        //左边的那个抽屉
        //drawerLayout = (DrawerLayout)findViewById(R.id.last1);
        //初始化 滑动控制器
        gestureDetector = new GestureDetector(MainActivity.this, onGestureListener);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {

        return super.onCreateView(name, context, attrs);
    }

    private GestureDetector.OnGestureListener onGestureListener =
            new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                       float velocityY) {
                    float x = e2.getX() - e1.getX();
                    float y = e2.getY() - e1.getY();

                    if (x > 0) {
                        doResult(0);
                    } else if (x < 0) {
                        doResult(1);
                    }
                    return true;
                }
            };

    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void doResult(int action) {
        switch (action) {
            case 1:
                //右滑
                if (i == 1) {
//                    toLove();
//                    i++;
                } else if (i == 2) {
                    toGril();
                    i++;
                } else if (i == 3) {
                    toMore();
                    i++;
                } else if (i == 4) {

                }
                break;

            case 0:
                //左滑
                if (i == 1) {
                    //drawerLayout.openDrawer(Gravity.LEFT);
                    //drawerLayout.openDrawer(Gravity.LEFT);
                } else if (i == 2) {
                    toFirst();
                    i--;
                } else if (i == 3) {
                    toLove();
                    i--;
                } else if (i == 4) {
                    toGril();
                    i--;
                }
                break;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击第一个的时候
            case R.id.tv_fisrt:
                i = 1;
                toFirst();
                break;
            case R.id.tv_love:
                i = 2;
                toLove();
                break;
            case R.id.tv_gril:
                i = 3;
                toGril();
                break;
            case R.id.tv_more:
                i = 4;
                toMore();
                break;
            default:
                break;
        }
    }

    //去第一个
    void toFirst() {
        if (firstFragment == null) {
            firstFragment = new FirstFragment();

        }
        addOrShowFragment(transaction, firstFragment);
        // 设置底部tab变化
        first.setTextColor(getResources().getColor(R.color.colorAccent));
        love.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        girl.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        more.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    //去爱好
    void toLove() {
        if (loveFragment == null) {
            loveFragment = new LoveFragment();
        }
        addOrShowFragment(transaction, loveFragment);
        // 设置底部tab变化
        first.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        love.setTextColor(getResources().getColor(R.color.colorAccent));
        girl.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        more.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    //去美女
    void toGril() {
        if (grilFragment == null) {
            grilFragment = new GrilFragment();
        }
        addOrShowFragment(transaction, grilFragment);
        // 设置底部tab变化
        first.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        love.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        girl.setTextColor(getResources().getColor(R.color.colorAccent));
        more.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    //去更多
    void toMore() {
        if (moreFragment == null) {
            moreFragment = new MoreFragment();
        }
        addOrShowFragment(transaction, moreFragment);
//        title.setVisibility(View.GONE);
//        title.setText("更多");
        // 设置底部tab变化
        first.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        love.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        girl.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        more.setTextColor(getResources().getColor(R.color.colorAccent));
    }

    //添加或者显示
    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment) {
        if (baseFragment == fragment)
            return;
        transaction = manager.beginTransaction();
        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(baseFragment)
                    .add(R.id.content_layout, fragment).commit();
        } else {
            transaction.hide(baseFragment).show(fragment).commit();
        }

        baseFragment = fragment;
    }
}