package com.wildwolf.mycsdn.parsenter;


import rx.Subscription;

/**
 * Created by ${wild00wolf} on 2016/11/18.
 */
public class BasePresenter<V> {

    public V mView;
    protected Subscription mSubscription;

    public void attach(V view) {
        mView = view;
    }

    public void detach() {
        mView = null;
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }
}
