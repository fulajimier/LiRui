package com.example.lirui.as.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

/**
 * Created by 275073 on 2016/11/30.
 */
//实现 listView 可以下拉加载的话  就是生成一个头一个尾 默认隐藏起来
public class MyListView extends ListView{
    Context context;
    View myView;
    public MyListView(Context context) {
        super(context);
        this.context = context;
        intiView();
    }
    void intiView(){
        LayoutInflater inflater = LayoutInflater.from(context);
       // inflater.
    }



}
