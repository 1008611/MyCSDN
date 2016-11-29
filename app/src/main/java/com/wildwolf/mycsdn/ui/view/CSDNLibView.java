package com.wildwolf.mycsdn.ui.view;


import com.wildwolf.mycsdn.data.CSDNLibData;

import java.util.List;

/**
 * Created by ${wild00wolf} on 2016/11/25.
 */
public interface CSDNLibView extends IBaseView{

    void onSuccess(List<CSDNLibData> data);
}
