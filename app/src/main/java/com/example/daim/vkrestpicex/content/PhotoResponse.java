package com.example.daim.vkrestpicex.content;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DAIM on 24.07.2017.
 */

public class PhotoResponse {

    @SerializedName("count")
    private Integer mCount;

    @SerializedName("items")
    private List<Photo> mPhotos = null;

    @SerializedName("more")
    private Integer mMore;

    public Integer getCount() {
        return mCount;
    }

    public void setCount(Integer count) {
        mCount = count;
    }

    public List<Photo> getPhotos() {
        return mPhotos;
    }

    public void setItems(List<Photo> photos) {
        mPhotos = photos;
    }

    public Integer getMore() {
        return mMore;
    }

    public void setMore(Integer more) {
        mMore = more;
    }

}
