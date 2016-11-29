package com.wildwolf.mycsdn.model.impl;


import com.wildwolf.mycsdn.api.SplashService;
import com.wildwolf.mycsdn.data.SplashData;
import com.wildwolf.mycsdn.model.ISplashModel;
import com.wildwolf.mycsdn.net.NetManager;

import rx.Observable;

/**
 * Created by ${wild00wolf} on 2016/11/18.
 */
public class SplashModelImpl implements ISplashModel {
    @Override
    public Observable<SplashData> getSplashPic() {
        SplashService service = NetManager.getInstance().creat(SplashService.class);
        return service.getSplashPic();
    }
}
