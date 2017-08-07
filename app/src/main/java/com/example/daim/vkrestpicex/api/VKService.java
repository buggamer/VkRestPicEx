package com.example.daim.vkrestpicex.api;

import com.example.daim.vkrestpicex.content.Response;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by DAIM on 22.07.2017.
 */

public interface VKService {

    @GET("/method/photos.getAll?v=5.67&skip_hidden=1&photo_sizes=1&extended=1")
    Observable<Response> photos(@Query("offset") int offset, @Query("count") int count);
}
