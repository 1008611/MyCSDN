package com.wildwolf.mycsdn.parsenter;


import com.wildwolf.mycsdn.data.SplashData;
import com.wildwolf.mycsdn.model.ISplashModel;
import com.wildwolf.mycsdn.model.impl.SplashModelImpl;
import com.wildwolf.mycsdn.rx.RxManager;
import com.wildwolf.mycsdn.rx.RxSubscriber;
import com.wildwolf.mycsdn.ui.view.SplashView;

/**
 * Created by ${wild00wolf} on 2016/11/18.
 */
public class SplashPresenter extends BasePresenter<SplashView> {

    private ISplashModel model;

    public SplashPresenter() {
        model = new SplashModelImpl();

    }

    public void getSplashPic() {
        mSubscription = RxManager.getInstance().doSubscribe(model.getSplashPic(), new RxSubscriber<SplashData>(false) {

            @Override
            protected void _onNext(SplashData splashData) {
                mView.onSuccess(splashData);
            }

            @Override
            protected void _onError() {
                mView.onError();
            }
        });
    }
}
