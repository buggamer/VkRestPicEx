package com.example.daim.vkrestpicex.content;

import com.google.gson.annotations.SerializedName;


public class Likes {

    @SerializedName("user_likes")
    private Integer mUserLikes;

    @SerializedName("count")
    private Integer mCount;

    public void setUserLikes(Integer userLikes) {
        mUserLikes = userLikes;
    }

    public void setCount(Integer count) {
        mCount = count;
    }

    public Integer getUserLikes() {
        return mUserLikes;
    }

    public Integer getCount() {
        return mCount;
    }
}
