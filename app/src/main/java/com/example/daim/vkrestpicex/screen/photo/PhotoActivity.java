package com.example.daim.vkrestpicex.screen.photo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;

import com.example.daim.vkrestpicex.R;
import com.example.daim.vkrestpicex.content.Photo;
import com.example.daim.vkrestpicex.utils.PhotoImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoActivity extends AppCompatActivity{

    public static final String EXTRA_PHOTO = "photo";
    public static final String IMAGE = "image";


    @BindView(R.id.bigImageView)
    ImageView mImageView;

    public static void navigate(@NonNull AppCompatActivity activity, @NonNull View transitionImage,
                                @NonNull Photo photo) {
        Intent intent = new Intent(activity, PhotoActivity.class);
        intent.putExtra(EXTRA_PHOTO, photo);

        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareWindowForAnimation();
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        Photo photo = getIntent().getParcelableExtra(EXTRA_PHOTO);
        showPhoto(photo);
    }

    private void showPhoto(@Nullable Photo photo) {
        PhotoImageUtils.loadPhoto(mImageView, photo.getBigPhotoUrl());
    }

    private void prepareWindowForAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }
}
