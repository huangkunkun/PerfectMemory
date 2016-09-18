package com.huangkun.perfectmemory.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 获取网络数据
 */
public class MyHttpUtil {

    /**
     * 解析json数据
     */
    public static String getTextFromUrl(URL requestUrl){
        String result = "";
        InputStream is = null;
        try{
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5 * 1000);
            connection.setConnectTimeout(5 * 1000);
            is = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            if ((line = reader.readLine()) != null){
                result += line;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static Bitmap getBitmapFromUrl(String requestUrl){
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == 200){
                is = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }
}
