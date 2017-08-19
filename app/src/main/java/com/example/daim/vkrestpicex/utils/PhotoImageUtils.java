package com.example.daim.vkrestpicex.utils;

import android.content.Context;
import android.widget.ImageView;

import com.example.daim.vkrestpicex.content.Photo;
import com.example.daim.vkrestpicex.content.PhotoUrl;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;



public class PhotoImageUtils {

    private final static int XSIZE = 604;
    private final static int YSIZE = 807;

    public static void loadPhoto(ImageView imageView, Photo photo, int width, int height) {
        int maxSize = Math.max(width, height);
        String url = getGalleryScreenPhotoUrl(maxSize, photo.getPhotoUrls());
        Picasso.with(imageView.getContext())
                .load(url)
                .noFade()
                .into(imageView);
    }

    public static void loadPhoto(ImageView imageView, String url){
        Picasso.with(imageView.getContext())
                .load(url)
                .noFade()
                .into(imageView);
    }


    private static String getGalleryScreenPhotoUrl(int maxSize, List<PhotoUrl> photoUrls){
        if(maxSize < YSIZE){
            for(int i = 0; i < photoUrls.size(); i++){
                if(photoUrls.get(i).getType().equals("x"))
                    return photoUrls.get(i).getUrl();
            }
        }else{
            for(int j = 0; j < photoUrls.size(); j++){
                if(photoUrls.get(j).getType().equals("y"))
                    return photoUrls.get(j).getUrl();
            }
        }
        return photoUrls.get(photoUrls.size() - 1).getUrl();
    }
}
