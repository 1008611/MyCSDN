package com.wildwolf.mycsdn.parsenter;

import android.util.Log;

import com.wildwolf.mycsdn.model.IBlogModel;
import com.wildwolf.mycsdn.model.impl.BlogModelImpl;
import com.wildwolf.mycsdn.rx.RxManager;
import com.wildwolf.mycsdn.rx.RxSubscriber;
import com.wildwolf.mycsdn.ui.view.BlogItemView;
import com.wildwolf.mycsdn.utils.JsoupUtil;


/**
 * Created by ${wild00wolf} on 2016/11/22.
 */
public class BlogItemPresenter extends BasePresenter<BlogItemView> {
    private IBlogModel mModel;

    public BlogItemPresenter() {
        mModel = new BlogModelImpl();
    }


    public void getBlogItemData(String cid, int page) {
        mSubscription = RxManager.getInstance().doSubscribe(mModel.getBlogData(cid,page), new RxSubscriber<String>(false) {
            @Override
            protected void _onNext(String s) {
                Log.e("TAG-",s);
                mView.onSuccess(JsoupUtil.parseTest2(s));
            }

            @Override
            protected void _onError() {
                mView.onError();
            }
        });
    }
}
