package com.example.daim.vkrestpicex.screen.photo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.daim.vkrestpicex.R;
import com.example.daim.vkrestpicex.content.Photo;
import com.example.daim.vkrestpicex.repository.SinglePhotoCache;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoActivity extends AppCompatActivity implements
        PhotoPagerAdapter.OnCLickViewListener{

    public static final String LOG_TAG = "PhotoActivity1";

    public static final String EXTRA_PHOTO = "photo";
    public static final String IMAGE_POSIION = "image_position";


    @BindView(R.id.photo_view_pager)
    PhotoViewPager mPagerView;

    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayot;

    @BindView(R.id.photo_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.photo_text)
    TextView mPhoto_textView;

    @BindView(R.id.text_comments)
    TextView mCommentsView;

    @BindView(R.id.text_likes)
    TextView mLikesView;

    @BindView(R.id.text_repos)
    TextView mReposView;

    @BindView(R.id.meta_info_view)
    LinearLayout mMetaInfoLayout;

    private int mPhotoPosition;
    private boolean mMetaInfoFlag = true;
    private PhotoPagerAdapter mPagerAdapter;

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
        setSupportActionBar(mToolbar);
        setToolbarPadding();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener((View v)->{onBackPressed();});
        mPhotoPosition = getIntent().getIntExtra(IMAGE_POSIION, 0);
        setMetaInfo(mPhotoPosition);

        mPagerAdapter = new PhotoPagerAdapter(SinglePhotoCache.getPhotos());
        mPagerAdapter.attachClickViewListener(this);
        mPagerView.setAdapter(mPagerAdapter);
        mPagerView.setCurrentItem(mPhotoPosition);

        setOnPagerChangeListener();
    }

    private void setOnPagerChangeListener(){

        mPagerView.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                setMetaInfo(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void setMetaInfo(int position){
        int photoNumb = position + 1;
        Photo photo = SinglePhotoCache.getPhotos().get(position);
        getSupportActionBar().setTitle(""+ photoNumb + " from " + SinglePhotoCache.getPhotoCountity());
        mPhoto_textView.setText(photo.getText());
        mLikesView.setText(photo.getLikes().getCount().toString());
        mReposView.setText(photo.getReposts().getCount().toString());
        //mCommentsView.setText(photo.getComments().getCount().toString());
    }

    private void setToolbarPadding(){
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) return;
        int result  = 0;
        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceId > 0){
            result = this.getResources().getDimensionPixelSize(resourceId);;
            mAppBarLayot.setPadding(0, result, 0, 0);
        }
    }


    @Override
    public void onClickView() {
        if(mMetaInfoFlag){
            mAppBarLayot.setVisibility(View.INVISIBLE);
            mMetaInfoLayout.setVisibility(View.INVISIBLE);
        }
        else{
            mAppBarLayot.setVisibility(View.VISIBLE);
            mMetaInfoLayout.setVisibility(View.VISIBLE);
        }
        mMetaInfoFlag = !mMetaInfoFlag;
    }
}
