package com.example.lirui.as.network;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 275073 on 2016/9/14.
 */
//用来 学习OkHttp的类
public class OKHttpUtil {
    public final static String ip = "http://192.168.20.193:8380/BDM/pdahttpservice?jsonStr=";
    public final static String myUrl = "{\"isMobile\":\"Y\",\"reqData\":[{\"body\":\"{\\\"pgmVer\\\":\\\"0.0.0.01\\\"}\",\"pdaInfo\":\"{\\\"deptCode\\\":null,\n" +
            "\\\"operType\\\":\\\"SYS_04\\\",\\\"pdaCode\\\":\\\"12345678\\\",\\\"pdaType\\\":\\\"PDAM_ACCT\\\",\\\"pgmVer\\\":\\\"0.0.0.01\\\",\\\"userCode\\\":null,\\\"userType\\\":\\\"COURIER\\\"}\"}]}\n";
    //OKHttp客户端
    static OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            //连接超时
            .connectTimeout(3000, TimeUnit.MILLISECONDS)
                    //读取超时
            .readTimeout(1000, TimeUnit.MILLISECONDS)
                    //写入超时
            .writeTimeout(1000, TimeUnit.MILLISECONDS)
            .build();


    //通过地址请求 返回 结果
    public String run(String url) throws Exception {
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            // Log.i("MyOKHttp", response.body().string());
            String s = response.body().string();
            return s;
        } else {
            throw new Exception("网络请求失败,失败返回码：" + response);
        }
    }

    public void download(final String url,ProgressDialog dialog) throws Exception {
        //申请WRITE_EXTERNAL_STORAGE权限

        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        int progress = 0;
        if (response.isSuccessful()) {
            InputStream is = null;
            byte[] buf = new byte[2048];
            int len = 0;
            FileOutputStream fos = null;
            String SDPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            try {
                is = response.body().byteStream();
                long total = response.body().contentLength();
                File file = new File(SDPath, "ocb.zip");
                Log.i("lirui",file.getPath());
                fos = new FileOutputStream(file);
                long sum = 0;
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                    sum += len;
                    progress = (int) (sum * 1.0f / total * 100);
                    Log.d("h_bl", "progress=" + progress);
                    dialog.incrementProgressBy(1);

//                    Message msg = handler.obtainMessage();
//                    msg.what = 0;
//                    msg.arg1 = progress;
//                    handler.sendMessage(msg);
                }
                fos.flush();

                Log.d("h_bl", "文件下载成功");
            } catch (Exception e) {
                throw new Exception("文件下载失败" + e.getMessage());
            } finally {
                try {
                    if (is != null)
                        is.close();
                } catch (IOException e) {
                    throw new Exception("关闭输出流失败" + response);
                }
                try {
                    if (fos != null)
                        fos.close();
                } catch (IOException e) {
                    throw new Exception("关闭输入流失败" + response);
                }
            }
        } else {
            throw new Exception("网络请求失败,失败返回码：" + response);
        }
    }


}
