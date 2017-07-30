package com.example.daim.vkrestpicex.api;

import android.text.TextUtils;


import com.example.daim.vkrestpicex.utils.PreferenceUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.http.Url;

/**
 * Created by DAIM on 24.07.2017.
 */

public class ApiKeyInterceptor implements Interceptor{

    private final String mToken, mUserId;

    private ApiKeyInterceptor(){
        mToken = PreferenceUtils.getToken();
        mUserId = PreferenceUtils.getUserId();
    }

    public static ApiKeyInterceptor create(){ return new ApiKeyInterceptor(); }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if(TextUtils.isEmpty(mToken) || TextUtils.isEmpty(mUserId)){
            return chain.proceed(chain.request());
        }
        HttpUrl url = chain.request().url().newBuilder()
                .addQueryParameter("access_token", mToken)
                .addQueryParameter("owner_id", mUserId)
                .build();
        Request request = chain.request().newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
