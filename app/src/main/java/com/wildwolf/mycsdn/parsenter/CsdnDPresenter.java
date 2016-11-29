package com.wildwolf.mycsdn.parsenter;


import com.wildwolf.mycsdn.data.BlogData;
import com.wildwolf.mycsdn.model.ICsdnDModel;
import com.wildwolf.mycsdn.model.impl.CsdnDImpl;
import com.wildwolf.mycsdn.rx.RxManager;
import com.wildwolf.mycsdn.rx.RxSubscriber;
import com.wildwolf.mycsdn.ui.view.CsdnDView;
import com.wildwolf.mycsdn.utils.JsoupUtil;

import java.util.List;

/**
 * Created by ${wild00wolf} on 2016/11/24.
 */
public class CsdnDPresenter extends BasePresenter<CsdnDView> {

    private ICsdnDModel model;

    public CsdnDPresenter() {
        model = new CsdnDImpl();
    }


    public void getCsdnData(String name, int page) {
        mSubscription = RxManager.getInstance().doSubscribe(model.getCsdnData(name, page), new RxSubscriber<String>(false) {
            @Override
            protected void _onNext(String s) {
                List<BlogData> list = JsoupUtil.parseTest2(s);
                if (list.size() == 0) {
                    mView.onSuccess(JsoupUtil.parseOtherBlog(s));
                }

                mView.onSuccess(JsoupUtil.parseTest2(s));
            }

            @Override
            protected void _onError() {
                mView.onError();
            }
        });
    }
}
