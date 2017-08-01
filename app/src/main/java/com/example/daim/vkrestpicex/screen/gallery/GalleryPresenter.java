package com.example.daim.vkrestpicex.screen.gallery;

import android.support.annotation.NonNull;

import com.example.daim.vkrestpicex.content.Photo;
import com.example.daim.vkrestpicex.repository.RepositoryProvider;

import java.util.List;

/**
 * Created by DAIM on 24.07.2017.
 */

public class GalleryPresenter {

    private  GalleryView mGalleryView;
    private int mItemCount;
    private int mItemStep = 100;
    private boolean mIsAll = false;

    public GalleryPresenter(@NonNull GalleryView view){
        mGalleryView = view;
    }

    public void init(){
        newPhotosRequest();
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
