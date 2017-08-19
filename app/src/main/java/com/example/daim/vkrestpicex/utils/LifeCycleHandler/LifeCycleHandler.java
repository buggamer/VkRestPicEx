package com.example.daim.vkrestpicex.utils.LifeCycleHandler;


import android.support.annotation.NonNull;

import rx.Observable;

public interface LifeCycleHandler {

    @NonNull
    <T> Observable.Transformer<T, T> load(int id);

    @NonNull
    <T> Observable.Transformer<T, T> reload(int id);

    void clear(int id);

}
