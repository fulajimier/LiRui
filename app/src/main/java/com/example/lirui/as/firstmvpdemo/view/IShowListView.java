package com.example.lirui.as.firstmvpdemo.view;

import com.example.lirui.as.firstmvpdemo.bean.ViewEntity;

/**
 * Created by 275073 on 2016/10/24.
 */
//这个是对界面什么的操作的接口
public interface IShowListView {
    //显示一个消息的
    void showMsg(String s);
    //这个的话 是用来显示LitView的文字部分
    void showText(ViewEntity entity);
    //这个是用来显示 ListView的图片部分的
    void showImage(ViewEntity entity);
}
