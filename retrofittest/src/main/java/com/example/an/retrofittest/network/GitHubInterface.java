package com.example.an.retrofittest.network;


import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * description:
 * author: WDSG
 * date: 2017/3/6
 */
public interface GitHubInterface {
    //http://test.xdyapi.haodai.net/Wallet/getAccountList?os_type=1&appid=1&imei=867614023363542&app_version=35000&channel=web&auth_tms=20170306133730&auth_did=5793&auth_dsig=08526488a4ade5a8&auth_uid=193594&auth_usig=8493bf3e111114c9?os_type=1&appid=1&imei=867614023363542&app_version=35000&channel=web&auth_tms=20170306133730&auth_did=5793&auth_dsig=08526488a4ade5a8&auth_uid=193594&auth_usig=8493bf3e111114c9&xid=193594

    //这是retrofit版本
//    @POST("Wallet/{user}")
//    Call<Repo> listRepos(@Path("user") String user, @QueryMap Map<String, String> map);

    //这是retrofit2版本
    //注意:ResponseBody这个类需要导入"import okhttp3.ResponseBody"这个包的
    @POST("Wallet/{user}")
    Call<ResponseBody> listRepos(@Path("user") String user, @QueryMap Map<String, String> map);
}
