package com.example.lirui.as.fragment;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lirui.R;

import java.util.ArrayList;

/**
 * Created by 275073 on 2016/8/10.
 */
//首页
public class FirstFragment extends Fragment {
    View view;
    ViewPager viewPager;
    ArrayList<Fragment> pageViews;
    String[] list = {"新闻", "机密"};
    //滑动的管理
    private LayoutInflater mInflater;
    private LinearLayout ll;
    private ImageView cursor;
    private int bmpw = 0; // 游标宽度
    private int offset = 0;// // 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fist_fragment, container,
                false);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        pageViews = new ArrayList<>();
        pageViews.add(new OneFragemnt());
        pageViews.add(new TwoFragment());
        viewPager.setAdapter(new MyPager(getChildFragmentManager(), pageViews, list));
        viewPager.addOnPageChangeListener(new MyPageChangeListener());
        mInflater = LayoutInflater.from(getActivity());
        ll = (LinearLayout) view.findViewById(R.id.ll_main);
        initView();
        return view;
    }

    //动态生成 上面那几个字的方法
    public void initView() {

        for (int i = 0; i < list.length; i++) {

            View view = mInflater.inflate(R.layout.item_hlist,
                    ll, false);
            TextView txt = (TextView) view
                    .findViewById(R.id.item_h);
            txt.setText(list[i]);
            //确定是不是当前的这个 那啥
            if (i == currIndex) {
                txt.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            ll.addView(view);
        }
    }

    //监听器
    public class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        int i;
        //滑动完成的时候
        @Override
        public void onPageSelected(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            int a = currIndex;
            if (arg0 != currIndex) {
                TextView t = (TextView) ll.getChildAt(arg0);
                t.setTextColor(getResources().getColor(R.color.colorAccent));
                TextView t1 = (TextView) ll.getChildAt(a);
                t1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                currIndex = arg0;
            }
        }

        //滑动结束的时候
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    //初始化指示器位置
    public void initCursorPos() {
        // 初始化动画
        cursor = (ImageView) view.findViewById(R.id.cursor);
        bmpw = BitmapFactory.decodeResource(getResources(), R.drawable.a)
                .getWidth();// 获取图片宽度

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / pageViews.size() - bmpw) / 2;// 计算偏移量

        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        cursor.setImageMatrix(matrix);// 设置动画初始位置
    }


    class MyPager extends FragmentPagerAdapter {
        ArrayList<Fragment> list;
        String[] taglist;

        MyPager(FragmentManager FragmentManager, ArrayList<Fragment> paramArrayList, String[] paramArrayOfString) {
            super(FragmentManager);
            this.list = paramArrayList;
            this.taglist = paramArrayOfString;
        }

        @Override
        public Fragment instantiateItem(ViewGroup container, int position) {
            Fragment f = (Fragment) super.instantiateItem(container, position);
            return f;
        }

        @Override
        public Fragment getItem(int position) {
            return (Fragment) this.list.get(position);
        }

        @Override
        public int getCount() {
            return this.list.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return FragmentPagerAdapter.POSITION_NONE;
        }
    }
}
