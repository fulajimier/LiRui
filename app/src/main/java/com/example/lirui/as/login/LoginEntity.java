package com.example.lirui.as.login;

import java.io.Serializable;

/**
 * Created by 275073 on 2016/11/15.
 */
public class LoginEntity implements Serializable{
    //姓名
    private String name;

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    //密码
    private String passWord;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
