package com.example.lirui.as.network;

import java.io.Serializable;

/**
 * Created by 275073 on 2016/9/14.
 */
//用来 学习OkHttp的类
public class NetEntity implements Serializable {
    public String errCode;
    public String errId;
    public String errMsg;
    public String retStatus;
    public NetBody[] retValue;
}
