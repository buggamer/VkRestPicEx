package com.example.daim.vkrestpicex.utils.LifeCycleHandler;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import rx.Observable;

public class LoaderLifecycleHandler implements LifeCycleHandler{

    private final Context mContext;
    private final LoaderManager mLoaderManager;

    @NonNull
    public static LifeCycleHandler create(@NonNull Context context, @NonNull LoaderManager loaderManager) {
        return new LoaderLifecycleHandler(context, loaderManager);
    }

    private LoaderLifecycleHandler(@NonNull Context context, @NonNull LoaderManager loaderManager) {
        mContext = context;
        mLoaderManager = loaderManager;
    }

    @NonNull
    @Override
    public <T> Observable.Transformer<T, T> load(@IdRes final int loaderId) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(final Observable<T> observable) {
                if (mLoaderManager.getLoader(loaderId) == null) {
                    mLoaderManager.initLoader(loaderId, Bundle.EMPTY, new RxLoaderCallbacks<>(observable));
                }
                final RxLoader<T> loader = (RxLoader<T>) mLoaderManager.getLoader(loaderId);
                return loader.createObservable();
            }
        };
    }

    @NonNull
    @Override
    public <T> Observable.Transformer<T, T> reload(@IdRes final int loaderId) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(final Observable<T> observable) {
                mLoaderManager.restartLoader(loaderId, Bundle.EMPTY, new RxLoaderCallbacks<>(observable));
                final RxLoader<T> loader = (RxLoader<T>) mLoaderManager.getLoader(loaderId);
                return loader.createObservable();
            }
        };
    }

    @Override
    public void clear(int id) {
        mLoaderManager.destroyLoader(id);
    }

    private class RxLoaderCallbacks<D> implements LoaderManager.LoaderCallbacks<D> {

        private final Observable<D> mObservable;

        public RxLoaderCallbacks(@NonNull Observable<D> observable) {
            mObservable = observable;
        }

        @NonNull
        @Override
        public Loader<D> onCreateLoader(int id, Bundle args) {
            return new RxLoader<>(mContext, mObservable);
        }

        @Override
        public void onLoadFinished(Loader<D> loader, D data) {
            // Do nothing
        }

        @Override
        public void onLoaderReset(Loader<D> loader) {
            // Do nothing
        }
    }
}
