package com.example.derrick.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by derrick on 2018/8/3.
 */

public class Weather {
    public String status;
    public Api aqi;
    public Basic basic;
    public Now now;
    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<ForeCast> foreCastList;
}
