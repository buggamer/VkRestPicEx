package com.example.daim.vkrestpicex.screen.gallery;

import android.support.annotation.NonNull;


import com.example.daim.vkrestpicex.content.Photo;

import java.util.List;

/**
 * Created by DAIM on 24.07.2017.
 */

public interface GalleryView {

    void showPhotos(@NonNull List<Photo> photos);

    void showLoading();

    void hideLoading();

    void showError(Throwable throwable);
}
