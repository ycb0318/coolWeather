package com.example.derrick.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by derrick on 2018/8/3.
 */

public class Api {
    public City city;

    public class City{
        public String aqi;
        public String pm25;
        public String qlty;
    }
}
