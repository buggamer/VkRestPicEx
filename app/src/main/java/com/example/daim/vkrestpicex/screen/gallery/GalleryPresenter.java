package com.example.daim.vkrestpicex.screen.gallery;

import android.support.annotation.NonNull;

import repository.RepositoryProvider;

/**
 * Created by DAIM on 24.07.2017.
 */

public class GalleryPresenter {

    private  GalleryView mGalleryView;
    private int mItemCount;
    private int mItemOffset = 50;
    private boolean isAll = false;

    public GalleryPresenter(@NonNull GalleryView view){
        mGalleryView = view;
    }

    public void init(){
        RepositoryProvider.provideVKRepository()
                .photos()
                .doOnSubscribe(mGalleryView::showLoading)
                .doOnTerminate(mGalleryView::hideLoading)
                .subscribe(mGalleryView::showPhotos, throwable -> mGalleryView.showError(throwable));
    }
}
