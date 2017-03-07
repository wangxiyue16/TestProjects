package com.ethanco.retrofit2_0test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity {

    ///////////////////////////////////////////////////此API接口会过期，请在http://www.haoservice.com/docs/6中重新申请一次
    String apiKey = "214c68a19d674c1298cab87b02ef0dad";
    String baseUrl = "http://apis.haoservice.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        get();
        getByRxJava();
    }

    private void get() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
        service.loadeather("杭州", apiKey).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Response<Weather> response, Retrofit retrofit) {
                if (response.body() != null) {
                    Weather weather = response.body();
                    Weather.ResultEntity.TodayEntity todayEntiry = weather.getResult().getToday();
                    Log.i("zhk-MainActivity", "onResponse: 城市:" + todayEntiry.getCity() + " 温度:" + todayEntiry.getTemperature());
                } else {
                    Log.e("zhk-MainActivity", "onResponse: body==null");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("zhk-MainActivity", "onFailure: ", t);
            }
        });
    }

    private void getByRxJava() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
        Observable<Weather> observable = service.getWeatherData("杭州", apiKey);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Weather>() {
                    @Override
                    public void onCompleted() {
                        Log.i("zhk-MainActivity", "onCompleted: ");
                        Toast.makeText(getApplicationContext(),
                                "Completed",
                                Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("zhk-MainActivity", "onError: ", e);
                        Toast.makeText(getApplicationContext(),
                                "Error:" + e.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onNext(Weather weather) {
                        Weather.ResultEntity.TodayEntity todayEntiry = weather.getResult().getToday();
                        Log.i("zhk-MainActivity", "onNext: 城市:" + todayEntiry.getCity() + " 温度:" + todayEntiry.getTemperature());
                        Toast.makeText(MainActivity.this, "城市:" + todayEntiry.getCity() + " 温度:" + todayEntiry.getTemperature(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
