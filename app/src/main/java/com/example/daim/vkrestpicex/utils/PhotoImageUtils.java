package com.example.daim.vkrestpicex.utils;

import android.content.Context;
import android.widget.ImageView;

import com.example.daim.vkrestpicex.content.Photo;
import com.example.daim.vkrestpicex.content.PhotoUrl;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by DAIM on 28.07.2017.
 */

public class PhotoImageUtils {

    private final static int XSIZE = 604;
    private final static int YSIZE = 807;
    private final static int FETCH_COUNT = 8;

    public static void loadPhoto(ImageView imageView, Photo photo, int width, int height) {
        int maxSize = Math.max(width, height);
        String url = getGalleryScreenPhotoUrl(maxSize, photo.getPhotoUrls());
        Picasso.with(imageView.getContext())
                .load(url)
                .noFade()
                .into(imageView);
       /* Picasso.with(imageView.getContext())
                .load(url)
                .fetch(new Callback() {
                    @Override
                    public void onSuccess() {
                        Picasso.with(imageView.getContext())
                                .load(url)
                                .noFade()
                                .into(imageView);
                    }

                    @Override
                    public void onError() {

                    }
                });*/
    }

    public static void loadPhoto(ImageView imageView, String url){
        Picasso.with(imageView.getContext())
                .load(url)
                .noFade()
                .into(imageView);
    }

   /* public static void preFetchPhotos(List<Photo> photos, Context context){
        for(int i = 0; i < FETCH_COUNT; i++){
            String url = getGalleryScreenPhotoUrl(maxSize, photo.getPhotoUrls());
            Picasso.with(context)
                    .load(url)
                    .fetch();
        }
    }*/


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
