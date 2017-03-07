package com.example.an.retrofittest;

import android.util.Log;

import com.example.an.retrofittest.Activity.BaseActivity;
import com.example.an.retrofittest.bean.Repo;
import com.example.an.retrofittest.network.GitHubInterface;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends BaseActivity {
    private Retrofit retrofit;
    private GitHubInterface service;
    private Map map;
    private String TAG = getClass().getSimpleName();

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initTitleBar() {

    }

    @Override
    public void initData() {
        map = Conts.getConfigMap();
    }

    @Override
    public void findViews() {

    }

    @Override
    public void setViewsValue() {
        getDataFromNet();
    }

    //这是retrofit版本
//    @Override
//    public void getDataFromNet() {
//        retrofit = new Retrofit.Builder().baseUrl("http://test.xdyapi.haodai.net/")
//                .addConverterFactory(GsonConverterFactory.create()).build();
//        service = retrofit.create(GitHubInterface.class);
//        Call<Repo> repos = service.listRepos("getAccountList", map);
//        repos.enqueue(new Callback<Repo>() {
//            @Override
//            public void onResponse(Response<Repo> response, Retrofit retrofit) {
//                if (response.body() != null) {
//                    Repo repo = response.body();
//                    Log.i("zhk-MainActivity", "rs_code:" + repo.getRs_code() + " rs_msg:" + repo.getRs_msg());
//                    Log.i("zhk-MainActivity", "remain:" + repo.getDetails().getRemain()
//                            + " income:" + repo.getDetails().getIncome().get(1).getTitle()
//                            + " banner:" + repo.getDetails().getBanner().get(0).getTitle()
//                            + " inform:" + repo.getDetails().getInform().get(1)
//                            + " rank:" + repo.getDetails().getRank().get(1).getCity_name()
//                            + " config:" + repo.getDetails().getConfig().getWithdraw_token());
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//
//            }
//        });
//    }

    //这是retrofit2版本
    @Override
    public void getDataFromNet() {
        retrofit = new Retrofit.Builder().baseUrl("http://test.xdyapi.haodai.net/")
//                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(GitHubInterface.class);
        Call<ResponseBody> repos = service.listRepos("getAccountList", map);
        repos.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Gson gson = new Gson();
                try {
                    Repo repo = gson.fromJson(response.body().string(), Repo.class);
                    Log.i("zhk-MainActivity", "rs_code:" + repo.getRs_code() + " rs_msg:" + repo.getRs_msg());
                    Log.i("zhk-MainActivity", "remain:" + repo.getDetails().getRemain()
                            + " income:" + repo.getDetails().getIncome().get(1).getTitle()
                            + " banner:" + repo.getDetails().getBanner().get(0).getTitle()
                            + " inform:" + repo.getDetails().getInform().get(1)
                            + " rank:" + repo.getDetails().getRank().get(1).getCity_name()
                            + " config:" + repo.getDetails().getConfig().getWithdraw_token());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("zhk-MainActivity", "onFailure");
            }
        });
    }
}
