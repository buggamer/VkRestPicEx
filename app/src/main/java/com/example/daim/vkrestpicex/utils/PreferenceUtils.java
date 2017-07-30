package com.example.daim.vkrestpicex.utils;

import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;

/**
 * Created by DAIM on 22.07.2017.
 */

public final class PreferenceUtils {

    private static final String TOKEN_KEY = "access_token";
    private static final String USER_ID = "user_id";
    private static final String EXPIRES_IN = "expires_in";

    private PreferenceUtils(){}

    public static void saveToken(@NonNull String token){ Hawk.put(TOKEN_KEY, token);}

    public static void saveUserId(@NonNull String userId){ Hawk.put(USER_ID, userId);}

    public static void saveExpiresIn(@NonNull long expiresIn){ Hawk.put(EXPIRES_IN, expiresIn);}

    public static String getToken(){ return Hawk.get(TOKEN_KEY); }

    public static String getUserId(){ return Hawk.get(USER_ID); }

    public static long getExpiresIn(){ return Hawk.get(EXPIRES_IN); }
}
