package com.example.derrick.coolweather;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.derrick.coolweather.gson.ForeCast;
import com.example.derrick.coolweather.gson.Weather;
import com.example.derrick.coolweather.util.HttpUitl;
import com.example.derrick.coolweather.util.Utility;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private ScrollView weatherLayout;
    private TextView textCity;
    private TextView updateTime;
    private TextView weatherDegree;
    private TextView weatherInfo;
    private LinearLayout forecastLayout;
    private ScrollView weatherWarpLayout;
    private TextView aqiQuality;
    private TextView pmQuality;
    private ImageView bingPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherLayout = (ScrollView) findViewById(R.id.scroll_weather);
        textCity = (TextView) findViewById(R.id.text_city);
        updateTime = (TextView) findViewById(R.id.text_update);
        weatherDegree = (TextView) findViewById(R.id.text_degree_tmp);
        weatherInfo = (TextView) findViewById(R.id.text_info);
        forecastLayout = (LinearLayout) findViewById(R.id.forecast_layout);
        weatherWarpLayout = (ScrollView) findViewById(R.id.scroll_weather);
        aqiQuality = (TextView) findViewById(R.id.aqi_quality);
        pmQuality = (TextView) findViewById(R.id.pm2_5_quality);
        bingPic = (ImageView) findViewById(R.id.bing_pic);

        String weatherID = getIntent().getStringExtra("weatherID");
        requestWeather(weatherID);
//
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        String weatherString = prefs.getString("weather",null);
//        if(weatherString != null){
//            Weather weather = Utility.handleWeatherResponse(weatherString);
//            showWeatherInfo(weather);
//        }else{
//
//        }


    }


    private void requestWeather(String weatherId){
        String weatherAddress = "http://guolin.tech/api/weather?cityid="+weatherId+"&key=b038fb56fe6a4fdc8b3e0c8f6eaf3eef";

        HttpUitl.sendOKHttpRequest(weatherAddress, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this,"天气获取失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(responseText);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(weather != null && "ok".equals(weather.status)){
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather",responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        }else{
                            Toast.makeText(WeatherActivity.this,"天气获取失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void showWeatherInfo(Weather weather){
        String cityName = weather.basic.cityName;
        String uptime = weather.basic.update.updateTime.split(" ")[1];
        String degree = weather.now.tmp + "℃";
        String info = weather.now.cond.txt;



        textCity.setText(cityName);
        updateTime.setText(uptime);
        weatherDegree.setText(degree);
        weatherInfo.setText(info);

        forecastLayout.removeAllViews();
        for(ForeCast foreCast : weather.foreCastList){
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item,forecastLayout,false);
            TextView dateText = (TextView) view.findViewById(R.id.forecast_date);
            TextView infoText = (TextView) view.findViewById(R.id.forecast_info);
            TextView maxText = (TextView) view.findViewById(R.id.forecast_max);
            TextView minText = (TextView) view.findViewById(R.id.forecast_min);

            dateText.setText(foreCast.date);
            infoText.setText(foreCast.cond.txt_d);
            maxText.setText(foreCast.tmp.max+"℃");
            minText.setText(foreCast.tmp.min+"℃");

            forecastLayout.addView(view);
        }

        aqiQuality.setText(weather.aqi.city.aqi);
        pmQuality.setText(weather.aqi.city.pm25);

        Glide.with(this).load("https://cn.bing.com/az/hprichbg/rb/PortAntonio_ZH-CN10325538004_1920x1080.jpg").into(bingPic);
//        weatherWarpLayout.setVisibility(View.VISIBLE);
    }
}
