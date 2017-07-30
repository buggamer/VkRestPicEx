package com.example.daim.vkrestpicex.content;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DAIM on 24.07.2017.
 */

public class Photo {
    @SerializedName("id")
    private Integer mId;

    @SerializedName("album_id")
    private Integer mAlbumId;

    @SerializedName("owner_id")
    private Integer mOwnerId;

    @SerializedName("sizes")
    private List<PhotoUrl> mPhotoUrls;

    @SerializedName("text")
    private String mText;

    @SerializedName("date")
    private Integer mDate;

    @SerializedName("post_id")
    private Integer mPostId;

    @SerializedName("likes")
    private Likes mLikes;

    @SerializedName("reposts")
    private Reposts mReposts;

    @SerializedName("real_offset")
    private Integer mRealOffset;

    public Integer getId() {
        return mId;
    }

    public Integer getAlbumId() {
        return mAlbumId;
    }

    public Integer getOwnerId() {
        return mOwnerId;
    }

    public List<PhotoUrl> getPhotoUrls() {
        return mPhotoUrls;
    }

    public String getText() {
        return mText;
    }

    public Integer getDate() {
        return mDate;
    }

    public Integer getPostId() {
        return mPostId;
    }

    public Likes getLikes() {
        return mLikes;
    }

    public Reposts getReposts() {
        return mReposts;
    }

    public Integer getRealOffset() {
        return mRealOffset;
    }

}
