package com.wildwolf.mycsdn.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;


import com.wildwolf.mycsdn.R;
import com.wildwolf.mycsdn.data.SplashData;
import com.wildwolf.mycsdn.parsenter.SplashPresenter;
import com.wildwolf.mycsdn.ui.view.SplashView;
import com.wildwolf.mycsdn.utils.DateUtil;
import com.wildwolf.mycsdn.utils.ImageLoader;
import com.wildwolf.mycsdn.utils.SPUtil;

import butterknife.Bind;

/**
 * Created by ${wild00wolf} on 2016/11/18.
 */
public class SplashActivity extends BaseMvpActivity<SplashView, SplashPresenter> implements SplashView {

    @Bind(R.id.splash_iv)
    ImageView splashIv;
    private String mTimeLine;


    @Override
    protected SplashPresenter initPresenter() {
        return new SplashPresenter();
    }

    @Override
    protected void fetchData() {
        if (!DateUtil.formatDate().equals(mTimeLine)) {
            mPresenter.getSplashPic();
        }
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
//        if (!(Boolean) SPUtil.get(SetFragment.SPLASH, false) || !NetUtil.isConnected(mContext)) {
            ImageLoader.load(mContext, R.drawable.original_splash, splashIv);
//        } else {
//            ImageLoader.load(mContext, (String) SPUtil.get("splash_url", ""), splashIv);
//        }

        startDelay();

    }

    private void startDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    protected void initData() {
        mTimeLine = (String) SPUtil.get("splash_time", "");
    }

    @Override
    public void onSuccess(SplashData data) {
        SPUtil.save("splash_time", DateUtil.formatDate());
        SPUtil.save("splash_url", data.getUrl());
    }

    @Override
    public void onError() {

    }

}
