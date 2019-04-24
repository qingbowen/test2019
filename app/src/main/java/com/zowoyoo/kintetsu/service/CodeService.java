package com.zowoyoo.kintetsu.service;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CodeService {

    @GET
    Call<String> checkCode();


    @GET("qr.jsp")
    Call<String> checkCodeTest(@Query("code") String code);
}
