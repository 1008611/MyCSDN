package com.wildwolf.mycsdn.model;

import rx.Observable;

/**
 * Created by ${wild00wolf} on 2016/11/24.
 */
public interface ICsdnDModel {
    Observable<String> getCsdnData(String name, int page);
}

