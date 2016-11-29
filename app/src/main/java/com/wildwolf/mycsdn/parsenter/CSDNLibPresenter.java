package com.wildwolf.mycsdn.parsenter;


import android.util.Log;

import com.wildwolf.mycsdn.model.ICSDNLibModel;
import com.wildwolf.mycsdn.model.impl.CSDNLibModelImpl;
import com.wildwolf.mycsdn.rx.RxManager;
import com.wildwolf.mycsdn.rx.RxSubscriber;
import com.wildwolf.mycsdn.ui.view.CSDNLibView;
import com.wildwolf.mycsdn.utils.JsoupUtil;

/**
 * Created by ${wild00wolf} on 2016/11/25.
 */
public class CSDNLibPresenter extends BasePresenter<CSDNLibView> {
    private ICSDNLibModel mModel;

    public CSDNLibPresenter() {
        mModel = new CSDNLibModelImpl();
    }


    public void getCSDNLibData(String article,String type,int page) {
        mSubscription = RxManager.getInstance().doSubscribe(mModel.getCSDNLibData(article,type,page), new RxSubscriber<String>(false) {
            @Override
            protected void _onNext(String s) {
                Log.e("TAG-s", s);
                mView.onSuccess(JsoupUtil.parseCsdnLib(s));
            }

            @Override
            protected void _onError() {
                mView.onError();
            }
        });
    }
}

