package com.example.lirui.as.firstmvpdemo.modle;

import com.example.lirui.R;
import com.example.lirui.as.firstmvpdemo.bean.ViewEntity;

/**
 * Created by 275073 on 2016/10/24.
 */
public class UserModle implements IUserModle {
    @Override
    public ViewEntity setEntity() {
        ViewEntity entity = new ViewEntity();
        entity.setName("哈哈哈哈哈");
        entity.setDrawable(R.drawable.a);
        return entity;
    }
}
