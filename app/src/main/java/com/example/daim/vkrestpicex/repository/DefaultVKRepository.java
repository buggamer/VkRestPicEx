package com.example.daim.vkrestpicex.repository;

import android.util.Log;


import com.example.daim.vkrestpicex.api.ApiFactory;
import com.example.daim.vkrestpicex.content.Photo;
import com.example.daim.vkrestpicex.content.PhotoResponse;
import com.example.daim.vkrestpicex.utils.RxUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by DAIM on 22.07.2017.
 */

public class DefaultVKRepository implements VKRepository {

    private final String LOG_TAG = "DefaultVKRepository";

    @Override
    public Observable<List<Photo>> photos(int offset, int count) {
        return ApiFactory.getVKService()
                .photos(offset, count)
                .flatMap(response -> {
                    return Observable.just(response.getResponse());
                })
                .map(PhotoResponse::getPhotos)
                .flatMap(Observable::from)
                .doOnError(throwable -> {
                    Log.d(LOG_TAG, throwable.getMessage());
                })
                .toList()
                .compose(RxUtils.async());
    }
}
