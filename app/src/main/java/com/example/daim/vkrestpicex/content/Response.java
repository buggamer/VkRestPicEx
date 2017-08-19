package com.example.daim.vkrestpicex.content;

import com.google.gson.annotations.SerializedName;


public class Response {

    @SerializedName("response")
    private PhotoResponse mResponse;

    public PhotoResponse getResponse() {
        return mResponse;
    }

}
