package com.wildwolf.mycsdn.ui.adapter;

import android.content.Context;

import com.wildwolf.mycsdn.R;
import com.wildwolf.mycsdn.data.BlogData;
import com.wildwolf.mycsdn.ui.adapter.baseadapter.BaseAdapter;
import com.wildwolf.mycsdn.ui.adapter.baseadapter.ViewHolder;

import java.util.List;


/**
 * Created by ${wild00wolf} on 2016/11/24.
 */
public class CsdaDAdapter extends BaseAdapter<BlogData> {

    public CsdaDAdapter(Context context, List<BlogData> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, BlogData data) {
        holder.setText(R.id.blog_item_desc, data.getTitle());
        holder.setText(R.id.blog_item_publishedat, data.getSubtype());
    }


    @Override
    protected int getItemLayoutId() {
        return R.layout.item_blog_layout;
    }
}
