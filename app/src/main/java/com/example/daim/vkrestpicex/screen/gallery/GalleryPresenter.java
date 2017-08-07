package com.example.daim.vkrestpicex.screen.gallery;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.daim.vkrestpicex.content.Photo;
import com.example.daim.vkrestpicex.repository.RepositoryProvider;
import com.example.daim.vkrestpicex.repository.SinglePhotoCache;

import java.util.List;

/**
 * Created by DAIM on 24.07.2017.
 */

public class GalleryPresenter {

    private final static String LOG_TAG = "GalleryPresenter";

    private  GalleryView mGalleryView;
    private int mItemCount;
    private int mItemStep = 100;
    private boolean mIsAll = false;

    public GalleryPresenter(@NonNull GalleryView view){
        mGalleryView = view;
    }

    public void init(){
        if(SinglePhotoCache.getPhotos().size() > 0){
            mItemCount = SinglePhotoCache.getPhotos().size();
            if(mItemCount == SinglePhotoCache.getPhotoCountity()) mIsAll = true;
            mGalleryView.showPhotos(SinglePhotoCache.getPhotos());
        }else{
            newPhotosRequest();
        }
        Log.d(LOG_TAG, "PhotoCache.size(): " + SinglePhotoCache.getPhotos().size());
    }

    public void newPhotosRequest(){
        if(mIsAll) return;
        RepositoryProvider.provideVKRepository()
                .photos(mItemCount, mItemStep)
                .doOnSubscribe(mGalleryView::showLoading)
                .doOnTerminate(mGalleryView::hideLoading)
                .subscribe(this::newPhotosHandling, throwable -> mGalleryView.showError(throwable));
    }

    private void newPhotosHandling(List<Photo> photos){
        if(photos.size() > 0){
            mItemCount+=photos.size();
            if(photos.size() < mItemStep) mIsAll = true;
            mGalleryView.showPhotos(photos);
        }
    }
}
