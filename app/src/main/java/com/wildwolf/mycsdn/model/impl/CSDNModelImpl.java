package com.wildwolf.mycsdn.model.impl;


import com.wildwolf.mycsdn.api.CSDNService;
import com.wildwolf.mycsdn.model.ICSDNModel;
import com.wildwolf.mycsdn.net.NetManager;

import rx.Observable;

/**
 * Created by ${wild00wolf} on 2016/11/24.
 */
public class CSDNModelImpl implements ICSDNModel {

    @Override
    public Observable<String> getCSDNData(String cid, int page) {
        CSDNService service = NetManager.getInstance().create1(CSDNService.class);
        return service.getCSDNItemData(cid,page);
    }
}
