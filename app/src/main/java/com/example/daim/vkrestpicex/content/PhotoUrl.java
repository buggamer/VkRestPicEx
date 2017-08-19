package com.example.daim.vkrestpicex.content;

import com.google.gson.annotations.SerializedName;

public class PhotoUrl {

    @SerializedName("src")
    private String mUrl;

    @SerializedName("width")
    private String mWidth;

    @SerializedName("height")
    private String mHeight;

    @SerializedName("type")
    private String mType;

    public String getUrl() {
        return mUrl;
    }

    public String getWidth() {
        return mWidth;
    }

    public String getHeight() {
        return mHeight;
    }

    public String getType() {
        return mType;
    }
}
