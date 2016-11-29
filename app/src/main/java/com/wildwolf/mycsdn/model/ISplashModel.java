package com.wildwolf.mycsdn.model;


import com.wildwolf.mycsdn.data.SplashData;

import rx.Observable;

/**
 * Created by ${wild00wolf} on 2016/11/18.
 */
public interface ISplashModel {
    Observable<SplashData> getSplashPic();
}
