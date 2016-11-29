package com.wildwolf.mycsdn.ui.activity;

import android.os.Bundle;

import com.wildwolf.mycsdn.parsenter.BasePresenter;


/**
 * Created by ${wild00wolf} on 2016/11/18.
 */
public abstract class BaseMvpActivity<V, P extends BasePresenter<V>> extends BaseActivity {

    protected P mPresenter;
    protected abstract P initPresenter();
    protected abstract void fetchData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.attach((V) this);

        fetchData();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }
}
