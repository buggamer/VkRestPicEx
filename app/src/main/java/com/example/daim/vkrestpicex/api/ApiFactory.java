package com.example.daim.vkrestpicex.api;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;


import com.example.daim.vkrestpicex.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DAIM on 22.07.2017.
 */

public class ApiFactory {

    private static OkHttpClient sClient;

    private static volatile VKService sService;

    private ApiFactory(){}

    @NonNull
    public static VKService getVKService(){
        VKService service = sService;
        if(service == null){
            synchronized (ApiFactory.class){
                service = sService;
                if(service == null){
                    service = sService = buildRetrofit().create(VKService.class);
                }
            }
        }
        return service;
    }

    @NonNull
    private static Retrofit buildRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(getClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @NonNull
    private static OkHttpClient getClient(){
        OkHttpClient client = sClient;
        if(client == null){
            synchronized (ApiFactory.class){
                client = sClient;
                if(client == null){
                    client = sClient = buildClient();
                }
            }
        }
        return sClient;
    }

    @NonNull
    private static OkHttpClient buildClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .addInterceptor(ApiKeyInterceptor.create())
                .build();
    }

    public static void recreate(){
        sClient = null;
        sClient = getClient();
        sService = buildRetrofit().create(VKService.class);
    }
}
