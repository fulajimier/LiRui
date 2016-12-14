package com.example.lirui.as.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lirui.R;
import com.example.lirui.as.firstmvpdemo.MvpMainActivity;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * Created by 275073 on 2016/8/10.
 */
//爱好
public class LoveFragment extends Fragment {
    GridView gridView;
    List<Bitmap> list;
    //进度条显示
    Button bt_dialog;
    LayoutInflater inflater;
    PopupWindow popupwindow;

    @Override
    public View onCreateView(LayoutInflater inflaters, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflaters;
        View view = inflater.inflate(R.layout.love_fragment, container,
                false);

        list = new ArrayList<>();
//        Bitmap one = BitmapFactory.decodeResource(getResources(), R.mipmap.one);
//        Bitmap two = BitmapFactory.decodeResource(getResources(), R.mipmap.two);
//        Bitmap three = BitmapFactory.decodeResource(getResources(), R.mipmap.three);
//        Bitmap four = BitmapFactory.decodeResource(getResources(), R.mipmap.four);
//        Bitmap five = BitmapFactory.decodeResource(getResources(), R.mipmap.five);
//        Bitmap six = BitmapFactory.decodeResource(getResources(), R.mipmap.six);
//        Bitmap seven = BitmapFactory.decodeResource(getResources(), R.mipmap.seven);
//        Bitmap etiy = BitmapFactory.decodeResource(getResources(), R.mipmap.enity);
//        Bitmap night = BitmapFactory.decodeResource(getResources(), R.drawable.b);
        Bitmap one = BitmapFactory.decodeResource(getResources(), R.drawable.b);
        Bitmap two = BitmapFactory.decodeResource(getResources(), R.drawable.umeng_socialize_fav);
        Bitmap three = BitmapFactory.decodeResource(getResources(), R.drawable.umeng_socialize_more);
        Bitmap four = BitmapFactory.decodeResource(getResources(), R.drawable.umeng_socialize_qq);
        Bitmap five = BitmapFactory.decodeResource(getResources(), R.drawable.umeng_socialize_share_music);
        Bitmap six = BitmapFactory.decodeResource(getResources(), R.drawable.umeng_socialize_sina);
        Bitmap seven = BitmapFactory.decodeResource(getResources(), R.drawable.umeng_socialize_x_button);
        Bitmap etiy = BitmapFactory.decodeResource(getResources(), R.drawable.umeng_socialize_share_video);
        Bitmap night = BitmapFactory.decodeResource(getResources(), R.drawable.umeng_socialize_wechat);
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        list.add(five);
        list.add(six);
        list.add(seven);
        list.add(etiy);
        list.add(night);
        gridView = (GridView) view.findViewById(R.id.gv);
        gridView.setAdapter(new MyAdapter());
        bt_dialog = (Button) view.findViewById(R.id.p_dialog);
        bt_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动一个 显示进度的 进度框
                // 进度条还有二级进度条的那种形式，这里就不演示了
                final ProgressDialog dialog = new ProgressDialog(getActivity());
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置水平进度条
                dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
                dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
                dialog.setIcon(R.drawable.b);// 设置提示的title的图标，默认是没有的
                dialog.setTitle("提示");
                dialog.setMax(100);
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                            }
                        });
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                            }
                        });
                dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "中立",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                            }
                        });
                dialog.setMessage("这是一个水平进度条");
                dialog.show();
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        int i = 0;
                        while (i < 100) {
                            try {
                                Thread.sleep(200);
                                // 更新进度条的进度,可以在子线程中更新进度条进度
                                dialog.incrementProgressBy(1);
                                // dialog.incrementSecondaryProgressBy(10)//二级进度条更新方式
                                i++;

                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                        }
                        // 在进度条走完时删除Dialog
                        dialog.dismiss();

                    }
                }).start();
            }
        });
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Toast.makeText(getActivity(), "触摸时间", Toast.LENGTH_LONG).show();
//                if (popupwindow != null && popupwindow.isShowing()) {
//                    popupwindow.dismiss();
//                    popupwindow = null;
//                }
//                return true;
//            }
//        });
        return view;
    }

    //适配器
    private class MyAdapter extends BaseAdapter {


        private LayoutInflater inflater;

        public MyAdapter() {
            super();
            inflater = LayoutInflater.from(getActivity());

        }

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

        @SuppressWarnings("deprecation")
        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.grid_item, null);
            }
            ImageView img = (ImageView) convertView.findViewById(R.id.gv_img);
            img.setImageBitmap(list.get(position));
            TextView tv = (TextView) convertView.findViewById(R.id.gv_textview);
            tv.setText("第" + position + "个");
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (position) {
                        case 0:
                            //把弹出框要显示的东西
//                            ViewGroup viewGroup =
                            View popupView = inflater.inflate(R.layout.popup_layout, null, false);
                            //实例化 这个玩意  并且设置弹出框的大小
                            popupwindow = new PopupWindow(popupView, 600,
                                    800, true);
                            //设置 弹窗在屏幕的中间
                            popupwindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                            popupwindow.setAnimationStyle(R.style.AppTheme);
                            //设置点击 返回键的时候  让 这个消失
                         //   popupwindow.setBackgroundDrawable(new BitmapDrawable());
                            //点击按钮
                            Button button = (Button) popupView.findViewById(R.id.bt_popup);
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(getActivity(), "你点击了去嗨皮", Toast.LENGTH_LONG).show();
                                    popupwindow.dismiss();
                                }
                            });
                            //删除按钮
                            ImageView img = (ImageView)popupView.findViewById(R.id.img_button);
                            img.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    popupwindow.dismiss();
                                }
                            });
                            //     WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
                            //设置在外面点击的时候 取消这玩意
                          //  popupwindow.setOutsideTouchable(true);
                            //显示
                            popupwindow.showAsDropDown(popupView);
                            break;
                        case 1:
                             startActivity(new Intent(getActivity(), MvpMainActivity.class));
                            break;
                        default:
                            break;
                    }
                }
            });
            return convertView;
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (popupwindow!=null&&popupwindow.isShowing()) {
            popupwindow.dismiss();
        }

    }

    //销毁的时候调用
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (popupwindow!=null&&popupwindow.isShowing()) {
            popupwindow.dismiss();
        }
    }

}
