package com.example.derrick.coolweather.util;

import android.text.TextUtils;

import com.example.derrick.coolweather.db.City;
import com.example.derrick.coolweather.db.County;
import com.example.derrick.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by derrick on 2018/8/2.
 */

public class Utility {
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray provinceArr = new JSONArray(response);
                for(int i = 0; i < provinceArr.length(); i++){
                    JSONObject provinceObject = provinceArr.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.setProvinceName(provinceObject.getString("name"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String response,int parentProvinceID){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray cityArr = new JSONArray(response);
                for(int i = 0; i<cityArr.length(); i++){
                    JSONObject cityObject = cityArr.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setParentProvinceID(parentProvinceID);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountyResponse(String response,int parentCityID){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray countyArr = new JSONArray(response);
                for(int i = 0;i <countyArr.length(); i++){
                    JSONObject countyObject = countyArr.getJSONObject(i);
                    County county = new County();
                    county.setParentCityID(parentCityID);
                    county.setCountyName(countyObject.getString("name"));
                    county.setCountyWeatherID(countyObject.getString("weather_id"));

                    county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
