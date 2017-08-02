package com.minoapp.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Devin on 2017/6/21.
 */

public class RetrofitClient {
    //超时时间
    private static final int DEFAULT_TIMEOUT=5;
    private static ApiService apiService;
    private OkHttpClient okHttpClient;
    public static String baseUrl=ApiService.Base_URL;
    private static RetrofitClient mInstance;

    private RetrofitClient(){
        okHttpClient= new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService=retrofit.create(ApiService.class);
    }
    public static RetrofitClient getInstance(){
        if (mInstance==null){
            synchronized (RetrofitClient.class){
                mInstance=new RetrofitClient();
            }
        }
        return mInstance;
    }

    public ApiService getApiService(){
        return apiService;
    }
}
