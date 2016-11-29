package com.wildwolf.mycsdn.ui.view;


import com.wildwolf.mycsdn.data.BlogData;

import java.util.List;

/**
 * Created by ${wild00wolf} on 2016/11/22.
 */
public interface BlogItemView extends IBaseView{
    void onSuccess(List<BlogData> data);
}
