package com.example.derrick.coolweather.gson;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

/**
 * Created by derrick on 2018/8/3.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String cityWeatherID;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
