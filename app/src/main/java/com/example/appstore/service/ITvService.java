package com.example.appstore.service;

import com.example.appstore.appinfo.DataList;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface ITvService {
    @GET("apps/all/page/1")
    Call<DataList> get_apps(@Query("t") long time);

    @Streaming
    @GET("static/app/{package_id}.apk")
    Call<ResponseBody> download(@Path("package_id") String package_id);
}
