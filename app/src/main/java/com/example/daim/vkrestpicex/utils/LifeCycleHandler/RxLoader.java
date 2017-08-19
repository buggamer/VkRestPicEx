package com.example.daim.vkrestpicex.utils.LifeCycleHandler;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import rx.AsyncEmitter;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.MainThreadSubscription;
import rx.functions.Action1;

class RxLoader<T> extends Loader<T> {

    private Observable<T> mObservable;

    private AsyncEmitter<T> mEmitter;

    private Subscription mSubscription;

    private boolean mIsErrorReported = false;

    @Nullable
    private T mData;

    @Nullable
    private Throwable mError;

    private boolean mIsCompleted = false;

    public RxLoader(@NonNull Context context, @NonNull Observable<T> observable) {
        super(context);
        mObservable = observable;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        subscribe();
    }

    @NonNull
    Observable<T> createObservable() {
        return Observable.fromEmitter(new Action1<AsyncEmitter<T>>() {
            @Override
            public void call(AsyncEmitter<T> asyncEmitter) {
                mEmitter = asyncEmitter;
                mEmitter.setSubscription(new MainThreadSubscription() {
                    @Override
                    protected void onUnsubscribe() {
                        clearSubscription();
                    }
                });

                if (mData != null) {
                    mEmitter.onNext(mData);
                }
                if (mIsCompleted) {
                    mEmitter.onCompleted();
                } else if (mError != null && !mIsErrorReported) {
                    mEmitter.onError(mError);
                    mIsErrorReported = true;
                }
                subscribe();
            }
        }, AsyncEmitter.BackpressureMode.LATEST);
    }



    @Override
    protected void onReset() {
        clearSubscription();
        mObservable = null;
        mData = null;
        mError = null;
        mIsCompleted = false;
        mIsErrorReported = false;
        mEmitter = null;
        super.onReset();
    }

    private void clearSubscription() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
            mSubscription = null;
        }
    }

    private void subscribe() {
        if (mEmitter != null && mSubscription == null && !mIsCompleted && mError == null) {
            mSubscription = mObservable.subscribe(new LoaderSubscriber());
        }
    }

    private class LoaderSubscriber extends Subscriber<T> {

        @Override
        public void onNext(T d) {
            mData = d;
            if (mEmitter != null && isStarted()) {
                mEmitter.onNext(d);
            }
        }

        @Override
        public void onError(Throwable throwable) {
            mError = throwable;
            if (mEmitter != null && isStarted()) {
                mEmitter.onError(throwable);
                mIsErrorReported = true;
            }
        }

        @Override
        public void onCompleted() {
            mIsCompleted = true;
            if (mEmitter != null && isStarted()) {
                mEmitter.onCompleted();
            }
        }
    }
}
