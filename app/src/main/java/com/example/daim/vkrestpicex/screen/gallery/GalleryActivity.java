package com.example.daim.vkrestpicex.screen.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;


import com.example.daim.vkrestpicex.R;
import com.example.daim.vkrestpicex.content.Photo;
import com.example.daim.vkrestpicex.screen.general.LoadingDialog;
import com.example.daim.vkrestpicex.screen.general.LoadingView;
import com.example.daim.vkrestpicex.screen.photo.PhotoActivity;
import com.example.daim.vkrestpicex.widget.BaseAdapter;
import com.example.daim.vkrestpicex.widget.EmptyRecyclerView;
import com.example.daim.vkrestpicex.widget.PreCachingLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DAIM on 24.07.2017.
 */

public class GalleryActivity extends AppCompatActivity implements GalleryView,
        BaseAdapter.OnItemClickListener<Photo>, GalleryAdapter.OnPaginationListener{

    private final String LOG_TAG = "GalleryActivity";

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;

    private LoadingView mLoadingView;

    private GalleryAdapter mAdapter;

    private GalleryPresenter mPresenter;

    public static void start(@NonNull Activity activity){
        Intent intent = new Intent(activity, GalleryActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        int columns = 2;
        mRecyclerView.setLayoutManager(new PreCachingLayoutManager(getApplicationContext(), columns));
        mRecyclerView.setEmptyView(mEmptyView);

        mAdapter = createAdapter();
        mAdapter.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnPaginationRequest(this);

        mPresenter = new GalleryPresenter(this);
        mPresenter.init();
    }

    @Override
    public void showPhotos(@NonNull List<Photo> photos) {
        Log.d(LOG_TAG, "photos: " + photos.size());
        mAdapter.addDataSet(photos);
    }

    @Override
    public void showError(Throwable throwable) {
        Log.d(LOG_TAG, "error: " + throwable.getMessage());
        mAdapter.clear();
    }

    @Override
    public void onItemClick(@NonNull Photo item, @NonNull View view, @NonNull int position) {
        ImageView imageView = ButterKnife.findById(view, R.id.image);
        PhotoActivity.navigate(this, imageView, item, position);

    }

    @Override
    public void showLoading() {
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    private GalleryAdapter createAdapter(){
        TypedValue typedValue = new TypedValue();
        int actionBarHeight = getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)
                ? TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics())
                : 0;
        int imageHeight = (int) ((getResources().getDisplayMetrics().heightPixels - actionBarHeight) / 2);
        int imageWidth = getResources().getDisplayMetrics().widthPixels / 2;

        return new GalleryAdapter(new ArrayList(), imageHeight, imageWidth);
    }

    @Override
    public void paginationRequest() {
        mPresenter.newPhotosRequest();
    }
}
