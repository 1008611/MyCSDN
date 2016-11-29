package com.wildwolf.mycsdn.model.impl;



import com.wildwolf.mycsdn.api.CSDNLibService;
import com.wildwolf.mycsdn.model.ICSDNLibModel;
import com.wildwolf.mycsdn.net.NetManager;

import rx.Observable;

/**
 * Created by ${wild00wolf} on 2016/11/25.
 */
public class CSDNLibModelImpl implements ICSDNLibModel {

    @Override
    public Observable<String> getCSDNLibData(String article,String type,int page) {
        CSDNLibService service = NetManager.getInstance().create1(CSDNLibService.class);
        return service.getCSDNLibData(article,type,page);
    }
}
