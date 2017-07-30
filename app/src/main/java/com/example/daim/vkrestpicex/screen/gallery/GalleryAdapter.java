package com.example.daim.vkrestpicex.screen.gallery;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.example.daim.vkrestpicex.AppDelegate;
import com.example.daim.vkrestpicex.content.Photo;
import com.example.daim.vkrestpicex.widget.BaseAdapter;

import java.util.List;

/**
 * Created by DAIM on 24.07.2017.
 */

public class GalleryAdapter extends BaseAdapter<PhotoHolder, Photo> {

    private final String LOG_TAG = "GalleryAdapter";

    private final int mImageWidth;
    private final int mImageHeight;

    public GalleryAdapter(@NonNull List<Photo> items, int imageHeight, int imageWidth){
        super(items);
        mImageWidth = imageWidth;
        mImageHeight = imageHeight;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PhotoHolder.create(AppDelegate.getAppContext(), mImageHeight, mImageWidth);
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Photo photo = getItem(position);
        Log.d(LOG_TAG, "bindPhotoPosition: " + position);
        holder.bind(photo, position);
    }
}
