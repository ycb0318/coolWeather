package com.example.derrick.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by derrick on 2018/8/2.
 */

public class City extends DataSupport {
    private int id;
    private String cityName;
    private int cityCode;
    private int parentProvinceID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getParentProvinceID() {
        return parentProvinceID;
    }

    public void setParentProvinceID(int parentProvinceID) {
        this.parentProvinceID = parentProvinceID;
    }
}
