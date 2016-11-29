package com.wildwolf.mycsdn.rx;

import android.content.Context;
import android.widget.Toast;


import com.wildwolf.mycsdn.App;

import java.io.IOException;

import rx.Subscriber;

/**
 * Created by ${wild00wolf} on 2016/11/18.
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {

    private Context mContext;
    private boolean mIsShowLoading;

    public RxSubscriber(Boolean isShowLoading) {
        mContext = App.getContext();
        mIsShowLoading = isShowLoading;
    }

    @Override
    public void onCompleted() {
        cancelLoading();
    }

    @Override
    public void onError(Throwable e) {
        //统一处理请求异常的情况
        if (e instanceof IOException) {
            Toast.makeText(mContext, "网络链接异常...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        _onError();

        cancelLoading();
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onStart() {
        super.onStart();
        showLoading();
    }

    private void showLoading() {
    }

    private void cancelLoading() {
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError();
}
