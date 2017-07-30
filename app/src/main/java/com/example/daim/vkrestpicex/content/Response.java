package com.example.daim.vkrestpicex.content;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DAIM on 24.07.2017.
 */

public class Response {

    @SerializedName("response")
    private PhotoResponse mResponse;

    public PhotoResponse getResponse() {
        return mResponse;
    }

}
