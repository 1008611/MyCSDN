package com.wildwolf.mycsdn.parsenter;


import com.wildwolf.mycsdn.model.ICSDNModel;
import com.wildwolf.mycsdn.model.impl.CSDNModelImpl;
import com.wildwolf.mycsdn.rx.RxManager;
import com.wildwolf.mycsdn.rx.RxSubscriber;
import com.wildwolf.mycsdn.ui.view.CSDNItemView;
import com.wildwolf.mycsdn.utils.JsoupUtil;

/**
 * Created by ${wild00wolf} on 2016/11/24.
 */
public class CSDNItemPresenter extends BasePresenter<CSDNItemView> {
    private ICSDNModel mModel;

    public CSDNItemPresenter() {
        mModel = new CSDNModelImpl();
    }


    public void getCSDNItemData(String cid, int page) {
        mSubscription = RxManager.getInstance().doSubscribe(mModel.getCSDNData(cid, page), new RxSubscriber<String>(false) {
            @Override
            protected void _onNext(String s) {
                mView.onSuccess(JsoupUtil.parseCSDN(s));
            }

            @Override
            protected void _onError() {
                mView.onError();
            }
        });
    }
}

