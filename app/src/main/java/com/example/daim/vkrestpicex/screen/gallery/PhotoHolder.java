package com.example.daim.vkrestpicex.screen.gallery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.daim.vkrestpicex.R;
import com.example.daim.vkrestpicex.content.Photo;
import com.example.daim.vkrestpicex.utils.PhotoImageUtils;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DAIM on 24.07.2017.
 */

public class PhotoHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.image)
    ImageView mImageView;

    @BindView(R.id.text_view)
    TextView mTextView;

    private static int mWidth;
    private static int mHeight;


    private PhotoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @NonNull
    public static PhotoHolder create(@NonNull Context context, int imageHeight, int imageWidth){
        View view = View.inflate(context, R.layout.photo_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.height = imageHeight;
        layoutParams.width = imageWidth;
        imageView.requestLayout();
        mWidth = imageWidth;
        mHeight = imageHeight;
        return new PhotoHolder(view);
    }

    public void bind(@NonNull Photo photo, int position){

       // mTextView.setText(position + "");
        PhotoImageUtils.loadPhoto(mImageView, photo, mWidth, mHeight);
    }
}
