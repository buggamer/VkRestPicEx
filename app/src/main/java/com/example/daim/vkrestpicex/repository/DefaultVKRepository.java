package com.example.daim.vkrestpicex.repository;

import android.util.Log;


import com.example.daim.vkrestpicex.api.ApiFactory;
import com.example.daim.vkrestpicex.content.Photo;
import com.example.daim.vkrestpicex.content.PhotoResponse;
import com.example.daim.vkrestpicex.utils.RxUtils;

import java.util.List;

import rx.Observable;


public class DefaultVKRepository implements VKRepository {

    private final String LOG_TAG = "DefaultVKRepository";

    @Override
    public Observable<List<Photo>> photos(int offset, int count) {
        return ApiFactory.getVKService()
                .photos(offset, count)
                .flatMap(response -> {
                    return Observable.just(response.getResponse());
                })
                .doOnError(throwable -> {
                    Log.e(LOG_TAG, throwable.getMessage());
                })
                .map(response ->{
                    SinglePhotoCache.setPhotoCountity(response.getCount());
                    SinglePhotoCache.addPhotos(response.getPhotos());
                    return response;
                })
                .map(PhotoResponse::getPhotos)
                .flatMap(Observable::from)
                .toList()
                .compose(RxUtils.async());
    }
}
