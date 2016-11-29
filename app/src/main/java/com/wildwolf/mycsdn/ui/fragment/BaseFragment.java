package com.wildwolf.mycsdn.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.wildwolf.mycsdn.ui.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by ${wild00wolf} on 2016/11/18.
 */
public abstract class BaseFragment extends Fragment {

    protected BaseActivity mActivity;
    protected View mRootView;

    protected abstract int initLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initLayoutId();
        mRootView = inflater.inflate(initLayoutId(), container, false);
        ButterKnife.bind(this, mRootView);
        initView();
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
}
