package com.example.lirui.as.firstmvpdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lirui.R;
import com.example.lirui.as.firstmvpdemo.bean.ViewEntity;
import com.example.lirui.as.firstmvpdemo.modle.UserModle;
import com.example.lirui.as.firstmvpdemo.presenter.ListViewPresenter;
import com.example.lirui.as.firstmvpdemo.view.IShowListView;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by 275073 on 2016/10/24.
 */
//在MVP模式里面  activity就只处理界面相关的  其他的逻辑不需要管
public class MvpMainActivity extends SwipeBackActivity implements IShowListView {
    private SwipeBackLayout mSwipeBackLayout;
    //MVP 的
    ListViewPresenter presenter;
    //显示的text
    EditText editText;
    //显示的ListView
    ListView listView;
    List<ViewEntity> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvpview);
        //右滑的东西
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        //设置是否可以滑动
        mSwipeBackLayout.setEnableGesture(true);
        //获取屏幕的宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int phoneWidth = dm.widthPixels;
        //设置侧滑的区域为屏幕宽度的1/3，如果不设置系统默认为50dip
        mSwipeBackLayout.setEdgeSize(phoneWidth / 3);
        initView();
    }

    //初始化一些界面
    void initView() {
        //为界面设置点击事件
//        findViewById(R.id.mvp_main_view).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                InputMethodManager imm = (InputMethodManager)
//                        getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                //接下来就是 看一下点击的是几
//                return false;
//            }
//        });
        //操作类
        presenter = new ListViewPresenter(this);
        //显示搜索的一个文本框
        editText = (EditText) findViewById(R.id.name);
        //不默认 弹出 软件潘
        editText.clearFocus();
        //数据显示的listView
        listView = (ListView) findViewById(R.id.list_view);
        presenter.setListView();
    }

    @Override
    public void showMsg(String s) {
        editText.setHint(s);
    }

    @Override
    public void showText(ViewEntity entity) {
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void showImage(final ViewEntity entity) {
        list = new ArrayList<>();
        list.add(entity);
        list.add(entity);
        list.add(entity);
        list.add(entity);
        list.add(entity);
        list.add(entity);
        list.add(entity);
        list.add(entity);
        list.add(entity);
        list.add(entity);
        list.add(entity);
        list.add(entity);
        listView.setAdapter(new BaseAdapter() {
            LayoutInflater inflater = LayoutInflater.from(MvpMainActivity.this);

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.list_view_item, null);
                }
                ImageView img = (ImageView) convertView.findViewById(R.id.list_item_img);
                TextView txt = (TextView) convertView.findViewById(R.id.list_item_text);
                txt.setText(list.get(position).getName());
                img.setBackgroundResource(list.get(position).getDrawable());
                img.setBackgroundResource(list.get(position).getDrawable());
                //  img.setImageResource(list.get(position).getDrawable());
                // img.setImageDrawable(getResources().getDrawable(list.get(position).getDrawable()));

                return convertView;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //设置点击的时候 先把 键盘隐藏
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                //接下来就是 看一下点击的是几
                Toast.makeText(MvpMainActivity.this, "这是第" + position + "个", Toast.LENGTH_LONG).show();
            }
        });
        //隐藏右边的滚动条
//        listView.setVerticalScrollBarEnabled(false);
//        listView.setFastScrollEnabled(false);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            //滚动的时候操作的
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //设置点击的时候 先把 键盘隐藏

            }
        });
    }
}
