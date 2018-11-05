package com.example.administrator.weathertoday.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.weathertoday.R;
import com.example.administrator.weathertoday.dao.Example;
import com.example.administrator.weathertoday.manager.WeatherManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    TextView tvMain;
    TextView tvtemp;
    TextView tvpressure;
    TextView tvhumidity;
    TextView tvwindspeed;
    TextView tvwinddeg;
    EditText myCityName;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView myTv = (TextView) findViewById(R.id.myTv);
        tvMain = (TextView) findViewById(R.id.tvMain);
        tvtemp = (TextView) findViewById(R.id.tvtemp);
        tvpressure = (TextView) findViewById(R.id.tvpressure);
        tvhumidity = (TextView) findViewById(R.id.tvhumidity);
        tvwindspeed = (TextView) findViewById(R.id.tvwindspeed);
        tvwinddeg = (TextView) findViewById(R.id.tvwinddeg);
        myCityName = (EditText) findViewById(R.id.myCityName);
        btn = (Button) findViewById(R.id.btn);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Example> call = WeatherManager.getInstance().getApiService().loadPhotoList(myCityName.getText().toString(), "32c5a26ffa8529efd1f26064c22a49a4");
                call.enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        tvMain.setText("Main ");
                        tvtemp.setText("Temp ");
                        tvpressure.setText("Pressure ");
                        tvhumidity.setText("Humidity ");
                        tvwindspeed.setText("Wind Speed ");
                        tvwinddeg.setText("Wind Degree ");
                        if (response.isSuccessful()) {
                            Example ex = response.body();
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                            tvMain.setText("Main " + ex.getWeather().get(0).getMain());
                            tvtemp.setText("Temp " + String.valueOf(ex.getMain().getTemp()));
                            tvpressure.setText("Pressure " + String.valueOf(ex.getMain().getPressure()));
                            tvhumidity.setText("Humidity " + String.valueOf(ex.getMain().getHumidity()));
                            tvwindspeed.setText("Wind Speed " + String.valueOf(ex.getWind().getSpeed()));
                            tvwinddeg.setText("Wind Degree " + String.valueOf(ex.getWind().getDeg()));

                        } else {
                            Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "fail2", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }
}
