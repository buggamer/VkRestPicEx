package com.example.daim.vkrestpicex.screen.auth;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.example.daim.vkrestpicex.utils.AuthorizationUtils;
import com.example.daim.vkrestpicex.utils.PreferenceUtils;

/**
 * Created by DAIM on 28.07.2017.
 */

public class AuthPresenter {

    private final String LOG_TAG = "AuthPresenter";

    private AuthView mAuthView;

    public AuthPresenter(@NonNull AuthView authView){ mAuthView = authView; }

    public void init() {
        String token = PreferenceUtils.getToken();
        if (!TextUtils.isEmpty(token) && (System.currentTimeMillis() < PreferenceUtils.getExpiresIn())) {
            Log.d(LOG_TAG, "ture");
            mAuthView.openGalleryScreen();
        }else{
            Log.d(LOG_TAG, "false");
            mAuthView.loadAuthWebView(AuthorizationUtils.getAuthUrl());
        }
    }

    public void saveParseToken(String url){
        AuthorizationUtils.parseAndSaveToken(url);
        init();
    }
}
