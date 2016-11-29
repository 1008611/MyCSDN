package com.wildwolf.mycsdn;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.wildwolf.mycsdn.utils.SPUtil;

/**
 * Created by ${wild00wolf} on 2016/11/18.
 */
public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        initRealm();
        SPUtil.init(mContext, "sp");

        Fresco.initialize(mContext);
    }

    private void initRealm() {

    }

    public static Context getContext(){
        return mContext;
    }

}
