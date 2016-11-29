package com.wildwolf.mycsdn.ui.fragment;

import android.os.Bundle;

import com.wildwolf.mycsdn.parsenter.BasePresenter;


/**
 * Created by ${wild00wolf} on 2016/11/18.
 */
public abstract class BaseMvpFragment<V, P extends BasePresenter<V>> extends BaseFragment {

    protected static final String SUB_TYPE = "subtype";

    protected P mPresenter;

    protected abstract P initPresenter();

    protected abstract void fetchData();

    protected boolean mIsViewInitiated;
    protected boolean mIsVisibleToUser;
    protected boolean mIsDataInitiated;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;
        initFetchData();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.attach((V) this);

        mIsViewInitiated = true;
        initFetchData();
    }

    protected void initFetchData() {
        if (mIsVisibleToUser && mIsViewInitiated && !mIsDataInitiated) {
            fetchData();
            mIsDataInitiated = true;
        }
    }

    @Override
    public void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }
}
