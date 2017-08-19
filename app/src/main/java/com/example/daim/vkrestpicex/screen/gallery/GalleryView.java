package com.example.daim.vkrestpicex.screen.gallery;

import android.support.annotation.NonNull;


import com.example.daim.vkrestpicex.content.Photo;

import java.util.List;


public interface GalleryView {

    void showPhotos(@NonNull List<Photo> photos);

    void showLoading();

    void hideLoading();

    void showError(Throwable throwable);
}
