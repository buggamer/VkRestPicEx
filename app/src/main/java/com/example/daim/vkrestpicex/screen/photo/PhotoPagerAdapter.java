package com.example.daim.vkrestpicex.screen.photo;


import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.daim.vkrestpicex.content.Photo;
import com.example.daim.vkrestpicex.utils.PhotoImageUtils;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

public class PhotoPagerAdapter extends PagerAdapter{

    public final static String LOG_TAG = "PhotoPagerAdapter";

    private List<Photo> mPhotoList = new ArrayList<>();
    private OnCLickViewListener mListener;

    public PhotoPagerAdapter(List<Photo> photos){
        mPhotoList.addAll(photos);
    }

    public void attachClickViewListener(OnCLickViewListener listener){
        mListener = listener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(container.getContext());
        PhotoImageUtils.loadPhoto(photoView, mPhotoList.get(position).getBigPhotoUrl());
        photoView.setOnClickListener((View v)->{mListener.onClickView();});;
        container.addView(photoView);
        return photoView;
    }

    @Override
    public int getCount() {
        return mPhotoList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public interface OnCLickViewListener{
        public void onClickView();
    }
}
