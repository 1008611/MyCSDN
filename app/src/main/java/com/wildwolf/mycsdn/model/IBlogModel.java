package com.wildwolf.mycsdn.model;

import rx.Observable;

/**
 * Created by ${wild00wolf} on 2016/11/22.
 */
public interface IBlogModel {
    Observable<String> getBlogData(String cid, int page);
}
