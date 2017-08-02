package com.example.daim.vkrestpicex.screen.photo;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;

import com.example.daim.vkrestpicex.R;
import com.example.daim.vkrestpicex.content.Photo;
import com.example.daim.vkrestpicex.repository.SinglePhotoCache;
import com.example.daim.vkrestpicex.utils.PhotoImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoActivity extends AppCompatActivity{

    public static final String EXTRA_PHOTO = "photo";
    public static final String IMAGE = "image";
    public static final String IMAGE_POSIION = "image_position";


    @BindView(R.id.bigImageView)
    ImageView mImageView;

    @BindView(R.id.photo_toolbar)
    Toolbar mToolbar;

    private int mPhotoPosition;

    public static void navigate(@NonNull AppCompatActivity activity, @NonNull View transitionImage,
                                @NonNull Photo photo, @NonNull int position) {
        Intent intent = new Intent(activity, PhotoActivity.class);
        intent.putExtra(EXTRA_PHOTO, photo);
        intent.putExtra(IMAGE_POSIION, position);

        String transitionName = activity.getString(R.string.photo_image);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                transitionImage, transitionName);
        ActivityCompat.startActivity(activity,intent, options.toBundle());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        Photo photo = getIntent().getParcelableExtra(EXTRA_PHOTO);
        mPhotoPosition = getIntent().getIntExtra(IMAGE_POSIION, 0);
        showPhoto(photo);
    }

    private void showPhoto(@Nullable Photo photo) {
        PhotoImageUtils.loadPhoto(mImageView, photo.getBigPhotoUrl());
        mToolbar.setTitle(mPhotoPosition + " from " + SinglePhotoCache.getPhotoCountity());
    }


}
