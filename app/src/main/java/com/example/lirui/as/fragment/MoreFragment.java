package com.example.lirui.as.fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lirui.R;
import com.example.lirui.as.Entity;
import com.example.lirui.as.ImageShower;
import com.example.lirui.as.firstmvpdemo.MvpMainActivity;
import com.example.lirui.as.network.NetBody;
import com.example.lirui.as.network.NetEntity;
import com.example.lirui.as.network.OKHttpUtil;
import com.example.lirui.as.network.UplodEntity;
import com.example.lirui.as.util.CircularImage;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 275073 on 2016/8/10.
 */
public class MoreFragment extends Fragment {
    ListView listView;
    ProgressDialog dialog;
    List<Entity> list = new ArrayList<>();
    Gson gson = new Gson();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 2:
                    Toast.makeText(getActivity(), "下载失败了" + msg.obj.toString(), Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
            // Toast.makeText(getActivity(), "成功", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_fragment, container,
                false);
        CircularImage cover_user_photo = (CircularImage) view.findViewById(R.id.cover_user_photo);
        cover_user_photo.setImageResource(R.drawable.a);
        cover_user_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ImageShower.class));
            }
        });
        init();
        listView = (ListView) view.findViewById(R.id.lv);
        listView.setAdapter(new ListAdapter());
        //
//        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
//        setSwipeBackEnable(false); //首页禁止滑动删除
//        mSwipeBackLayout.setEdgeSize(480);
        return view;
    }

    void init() {
        list.add(new Entity("我的信息", R.drawable.b));
        list.add(new Entity("我的反馈", R.drawable.c));
        //进度条显示
        dialog = new ProgressDialog(getActivity());
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
                        dialog.dismiss();

                    }
                });
        dialog.setMessage("文件下载中");
    }

    //适配器
    private class ListAdapter extends BaseAdapter {


        private LayoutInflater inflater;

        public ListAdapter() {
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
                convertView = inflater.inflate(R.layout.item, null);
            }
            ImageView img = (ImageView) convertView.findViewById(R.id.itm_img);
            TextView txt = (TextView) convertView.findViewById(R.id.itm_text);
            img.setImageDrawable(getResources().getDrawable(list.get(position).add));
            txt.setText(list.get(position).text);
            //点击item时候的事件
            convertView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                   switch (list.get(position).text) {
                                                       case "我的信息":
                                                           startActivity(new Intent(getActivity(), MvpMainActivity.class));
                                                           break;
                                                       case "我的反馈":
                                                           //请求网络

                                                           new Thread() {
                                                               @Override
                                                               public void run() {
                                                                   Looper.prepare();
                                                                   dialog.show();
                                                                   try {
                                                                       OKHttpUtil util = new OKHttpUtil();
                                                                       String s = util.run("http://192.168.20.193:8380/BDM/pdahttpservice?jsonStr={\"isMobile\":\"Y\",\"reqData\":[{\"body\":\"{\\\"pgmVer\\\":\\\"0.0.0.01\\\"}\",\"pdaInfo\":\"{\\\"deptCode\\\":null,\n" +
                                                                               "\\\"operType\\\":\\\"SYS_04\\\",\\\"pdaCode\\\":\\\"12345678\\\",\\\"pdaType\\\":\\\"PDAM_ACCT\\\",\\\"pgmVer\\\":\\\"0.0.0.01\\\",\\\"userCode\\\":null,\\\"userType\\\":\\\"COURIER\\\"}\"}]}\n");
                                                                       NetEntity entity = gson.fromJson(s, NetEntity.class);
                                                                       NetBody en = entity.retValue[0];
                                                                       //下载路径 的 实体
                                                                       UplodEntity upEn = gson.fromJson(en.retValue.toString(), UplodEntity.class);
                                                                       Log.i("mylirui", upEn.updUrl);
                                                                       if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                                                               != PackageManager.PERMISSION_GRANTED) {
                                                                           //申请WRITE_EXTERNAL_STORAGE权限
                                                                           ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1
                                                                           );
                                                                       }
                                                                       util.download(upEn.updUrl, dialog);
                                                                       dialog.dismiss();
                                                                   } catch (Exception e) {
                                                                       //下载失败了
                                                                       Message msg1 = handler.obtainMessage();
                                                                       msg1.what = 2;
                                                                       msg1.obj = new String(e.getMessage());
                                                                       handler.sendMessage(msg1);
                                                                   }
                                                                   Looper.loop();
                                                               }
                                                           }.start();
                                                           break;
                                                       default:
                                                           break;
                                                   }
                                               }
                                           }
            );
            return convertView;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null) {
                    fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
            }
        }
    }
}

