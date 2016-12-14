package com.example.lirui.as.firstmvpdemo.presenter;

import com.example.lirui.as.firstmvpdemo.bean.ViewEntity;
import com.example.lirui.as.firstmvpdemo.modle.IUserModle;
import com.example.lirui.as.firstmvpdemo.modle.UserModle;
import com.example.lirui.as.firstmvpdemo.view.IShowListView;

/**
 * Created by 275073 on 2016/11/30.
 */
public class ListViewPresenter {
    private IShowListView showListView;
    private IUserModle userModle;
    //构造方法
    public ListViewPresenter(IShowListView showListView){
        this.showListView = showListView;
        userModle = new UserModle();
    }
   public void setListView(){
      showListView.showImage(userModle.setEntity());
       //showListView.showText(userModle.setEntity(entity));
       showListView.showMsg("随便瞎jb弹出个消息 给你瞧瞧");
   }
}
