package com.example.administrator.weathertoday.manager.http;

import com.example.administrator.weathertoday.dao.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterface {
    //http://api.openweathermap.org/data/2.5/weather?q=bangkok&appid=32c5a26ffa8529efd1f26064c22a49a4
//    @POST("weather?q=london&appid=32c5a26ffa8529efd1f26064c22a49a4")
//    Call<Example> loadPhotoList();  //This Method is fine because I fixed london as cityName

    @GET("weather")
    Call<Example> loadPhotoList(@Query("q") String cityName,@Query("appid") String appid);



//    @GET("weather")
//    Call<WeatherEnt> getWeatherData(@Query("q") String query,
//                                    @Query("units") String units,
//                                    @Query("appid") String appid);
}
