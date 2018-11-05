package com.example.administrator.weathertoday.manager;

import android.content.Context;

import com.example.administrator.weathertoday.manager.http.WeatherInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class WeatherManager {

    private static WeatherManager instance;

    public static WeatherManager getInstance() {
        if (instance == null)
            instance = new WeatherManager();
        return instance;
    }

    private Context mContext;

    private WeatherInterface apiService;

    public WeatherInterface getApiService() {
        return apiService;
    }

    private WeatherManager() {
        mContext = Contextor.getInstance().getContext();

        //http://api.openweathermap.org/data/2.5/weather?q=bangkok&appid=32c5a26ffa8529efd1f26064c22a49a4
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(WeatherInterface.class);
    }

}
