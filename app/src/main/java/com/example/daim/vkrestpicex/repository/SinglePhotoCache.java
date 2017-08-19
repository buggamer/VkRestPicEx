package com.example.daim.vkrestpicex.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.example.daim.vkrestpicex.content.Photo;

import java.util.ArrayList;
import java.util.List;



public class SinglePhotoCache {

    private static List<Photo> mPhotos;

    private static int mPhotoCountity;

    private SinglePhotoCache(){}

    @MainThread
    public static void init() { mPhotos = new ArrayList<>(); }

    public static void addPhotos(@NonNull List<Photo> photos) { mPhotos.addAll(photos);  }

    public static List<Photo> getPhotos() { return mPhotos; }

    public static void setPhotoCountity(int photoCountity) { mPhotoCountity = photoCountity; }

    public static int getPhotoCountity() { return mPhotoCountity; }
}
