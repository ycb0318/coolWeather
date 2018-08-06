package com.example.derrick.coolweather.gson;

/**
 * Created by derrick on 2018/8/3.
 */

public class ForeCast {
    public String date;

    public Temperature tmp;
    public Cond cond;

    public class Temperature{
        public String max;
        public String min;
    }

    public class Cond{
        public String txt_d;
    }

}
