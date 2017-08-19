package com.example.daim.vkrestpicex;


import android.app.Application;
import android.support.annotation.NonNull;

import com.example.daim.vkrestpicex.api.ApiFactory;
import com.example.daim.vkrestpicex.repository.RepositoryProvider;
import com.example.daim.vkrestpicex.repository.SinglePhotoCache;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;
import com.squareup.picasso.Picasso;

public class AppDelegate extends Application {

    private static AppDelegate sInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        sInstance = this;

        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSharedPrefStorage(this))
                .setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE)
                .build();

        ApiFactory.recreate();
        RepositoryProvider.init();
        SinglePhotoCache.init();

        Picasso picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(this))
                .build();
        Picasso.setSingletonInstance(picasso);

    }

    @NonNull
    public static AppDelegate getAppContext() { return sInstance; }

}
