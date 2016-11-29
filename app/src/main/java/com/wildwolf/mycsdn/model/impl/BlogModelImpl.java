package com.wildwolf.mycsdn.model.impl;



import com.wildwolf.mycsdn.api.BlogItemService;
import com.wildwolf.mycsdn.model.IBlogModel;
import com.wildwolf.mycsdn.net.NetManager;

import rx.Observable;

/**
 * Created by ${wild00wolf} on 2016/11/22.
 */
public class BlogModelImpl implements IBlogModel {

    @Override
    public Observable<String> getBlogData(String cid,int page) {
        BlogItemService service = NetManager.getInstance().create1(BlogItemService.class);
        return service.getBlogItemData(cid, page);
    }
}
