package com.wildwolf.mycsdn.model.impl;



import com.wildwolf.mycsdn.api.CsdnDService;
import com.wildwolf.mycsdn.model.ICsdnDModel;
import com.wildwolf.mycsdn.net.NetManager;

import rx.Observable;

/**
 * Created by ${wild00wolf} on 2016/11/24.
 */
public class CsdnDImpl implements ICsdnDModel {
    @Override
    public Observable<String> getCsdnData(String name,int page) {
        CsdnDService service = NetManager.getInstance().create1(CsdnDService.class);
        return service.getBlogItemData(name,page);
    }
}
