package com.example.daim.vkrestpicex.utils;

import android.widget.ImageView;

import com.example.daim.vkrestpicex.content.Photo;
import com.squareup.picasso.Picasso;

/**
 * Created by DAIM on 28.07.2017.
 */

public class PhotoImageUtils {

    public static void loadPhoto(ImageView imageView, Photo photo, int width, int height) {
        String url = photo.getPhotoUrls().toString();
        Picasso.with(imageView.getContext())
                .load(url)
                .noFade()
                .into(imageView);
    }
}
