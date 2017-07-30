package com.example.daim.vkrestpicex.repository;


import com.example.daim.vkrestpicex.content.Photo;

import java.util.List;

import rx.Observable;

/**
 * Created by DAIM on 22.07.2017.
 */

public interface VKRepository {

    public Observable<List<Photo>> photos(int offset, int count);

}
