package com.example.derrick.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by derrick on 2018/8/2.
 */

public class County extends DataSupport {
    private int id;
    private String countyName;
    private int parentCityID;
    private String countyWeatherID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getParentCityID() {
        return parentCityID;
    }

    public void setParentCityID(int parentCityID) {
        this.parentCityID = parentCityID;
    }

    public String getCountyWeatherID() {
        return countyWeatherID;
    }

    public void setCountyWeatherID(String countyWeatherID) {
        this.countyWeatherID = countyWeatherID;
    }
}
