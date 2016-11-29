package com.wildwolf.mycsdn.ui.adapter;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wildwolf.mycsdn.R;
import com.wildwolf.mycsdn.data.CSDNLibData;
import com.wildwolf.mycsdn.ui.adapter.baseadapter.BaseAdapter;
import com.wildwolf.mycsdn.ui.adapter.baseadapter.ViewHolder;

import java.util.List;


/**
 * Created by ${wild00wolf} on 2016/11/25.
 */
public class CSDNLibAdapter extends BaseAdapter<CSDNLibData> {

    public CSDNLibAdapter(Context context, List<CSDNLibData> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, CSDNLibData data) {
        holder.setText(R.id.lib_item_title, data.getName());

        SimpleDraweeView image = holder.getView(R.id.lib_item_icon);
        image.setImageURI(Uri.parse(data.getIconUrl()));

        SimpleDraweeView image2 = holder.getView(R.id.lib_item_img);
        image2.setImageURI(Uri.parse(data.getImgUrl()));


    }


    @Override
    protected int getItemLayoutId() {
        return R.layout.item_lib_layout;
    }
}
