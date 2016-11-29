package com.wildwolf.mycsdn.ui.view;


import com.wildwolf.mycsdn.data.BlogData;

import java.util.List;

/**
 * Created by ${wild00wolf} on 2016/11/24.
 */
public interface CsdnDView extends IBaseView{
    void onSuccess(List<BlogData> data);
}
