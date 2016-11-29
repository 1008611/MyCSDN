package com.wildwolf.mycsdn.model;

import rx.Observable;

/**
 * Created by ${wild00wolf} on 2016/11/25.
 */
public interface ICSDNLibModel {
    Observable<String> getCSDNLibData(String article, String sub, int page);
}
