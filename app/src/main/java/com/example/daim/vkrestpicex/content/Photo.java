package com.example.daim.vkrestpicex.content;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DAIM on 24.07.2017.
 */

public class Photo implements Parcelable{
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

    @SerializedName("comments")
    private Comments mComments;

    public Comments getComments() {
        return mComments;
    }

    private String mBigPhotoUrl;

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

    public String getBigPhotoUrl() { return mPhotoUrls.get(mPhotoUrls.size() - 1).getUrl(); }

    public Photo(Parcel in){
        mId = in.readInt();
        mText = in.readString();
        mDate = in.readInt();
        mBigPhotoUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mText);
        parcel.writeInt(mDate);
        String bigPhotoUrl = mPhotoUrls.get(mPhotoUrls.size() - 1).getUrl();
        parcel.writeString(bigPhotoUrl);
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {

        @Override
        public Photo createFromParcel(Parcel parcel) {
            return new Photo(parcel);
        }

        @Override
        public Photo[] newArray(int i) {
            return new Photo[i];
        }
    };
}
