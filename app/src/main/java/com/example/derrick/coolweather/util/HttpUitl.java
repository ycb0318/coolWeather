package com.example.derrick.coolweather.util;

import com.google.gson.annotations.SerializedName;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by derrick on 2018/8/2.
 */

public class HttpUitl {

    public static void sendOKHttpRequest(String url,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(callback);
    }
}
