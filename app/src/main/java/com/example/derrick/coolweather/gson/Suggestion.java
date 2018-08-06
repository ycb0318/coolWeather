package com.example.derrick.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by derrick on 2018/8/3.
 */

public class Suggestion {
    @SerializedName("comf")
    public Comfort comfort;

    @SerializedName("cw")
    public CarWash carWash;

    public Sport sport;

    public class Comfort{
        public String txt;
    }

    public class CarWash{
        public String txt;
    }


    public class Sport{
        public String txt;
    }

}
