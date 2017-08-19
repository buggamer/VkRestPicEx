package com.example.daim.vkrestpicex.screen.auth;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.example.daim.vkrestpicex.utils.AuthorizationUtils;
import com.example.daim.vkrestpicex.utils.PreferenceUtils;


public class AuthPresenter {

    private final String LOG_TAG = "AuthPresenter";

    private AuthView mAuthView;

    public AuthPresenter(@NonNull AuthView authView){ mAuthView = authView; }

    public void init() {
        String token = PreferenceUtils.getToken();
        if (!TextUtils.isEmpty(token) && (System.currentTimeMillis() < PreferenceUtils.getExpiresIn())) {
            mAuthView.openGalleryScreen();
        }else{
            mAuthView.loadAuthWebView(AuthorizationUtils.getAuthUrl());
        }
    }

    public void saveParseToken(String url){
       boolean gotToken =  AuthorizationUtils.parseAndSaveToken(url);
       if(gotToken) init();
    }
}
