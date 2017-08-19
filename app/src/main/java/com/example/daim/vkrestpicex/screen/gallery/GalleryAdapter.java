package com.example.daim.vkrestpicex.screen.gallery;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;


import com.example.daim.vkrestpicex.AppDelegate;
import com.example.daim.vkrestpicex.content.Photo;
import com.example.daim.vkrestpicex.widget.BaseAdapter;

import java.util.List;


public class GalleryAdapter extends BaseAdapter<PhotoHolder, Photo> {

    private final String LOG_TAG = "GalleryAdapter";

    private final int mImageWidth;
    private final int mImageHeight;
    private final int mPaginationStep = 20;

    private  OnPaginationListener mOnPaginationListener;

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
        super.onBindViewHolder(holder, position);;
        Photo photo = getItem(position);
        holder.bind(photo, position);
        if((getItemCount() - position) == mPaginationStep) mOnPaginationListener.paginationRequest();
    }

    public void setOnPaginationRequest(OnPaginationListener listener){
        mOnPaginationListener = listener;
    }

    public interface OnPaginationListener {
        public void paginationRequest();
    }
}
