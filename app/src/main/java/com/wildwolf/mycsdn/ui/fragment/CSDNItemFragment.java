package com.wildwolf.mycsdn.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.wildwolf.mycsdn.R;
import com.wildwolf.mycsdn.data.CSDNData;
import com.wildwolf.mycsdn.parsenter.CSDNItemPresenter;
import com.wildwolf.mycsdn.ui.activity.CSDNListActivity;
import com.wildwolf.mycsdn.ui.adapter.CSDNItemAdapter;
import com.wildwolf.mycsdn.ui.adapter.baseadapter.OnItemClickListeners;
import com.wildwolf.mycsdn.ui.adapter.baseadapter.OnLoadMoreListener;
import com.wildwolf.mycsdn.ui.adapter.baseadapter.ViewHolder;
import com.wildwolf.mycsdn.ui.view.CSDNItemView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by ${wild00wolf} on 2016/11/24.
 */
public class CSDNItemFragment extends BaseMvpFragment<CSDNItemView, CSDNItemPresenter> implements CSDNItemView, SwipeRefreshLayout.OnRefreshListener {

    private int PAGE_COUNT = 1;
    private String mSubtype;
    private int mTempPageCount = 2;

    private CSDNItemAdapter csdnItemAdapter;

    private boolean isLoadMore;

    @Bind(R.id.type_item_recyclerview)
    RecyclerView mRecyclerView;

    @Bind(R.id.type_item_swipfreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static CSDNItemFragment newInstance(String subtype) {
        CSDNItemFragment fragment = new CSDNItemFragment();
        Bundle arguments = new Bundle();
        arguments.putString(SUB_TYPE, subtype);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    protected CSDNItemPresenter initPresenter() {
        return new CSDNItemPresenter();
    }

    @Override
    protected void fetchData() {
        mPresenter.getCSDNItemData(mSubtype, PAGE_COUNT);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_type_item_layout;
    }

    @Override
    protected void initView() {

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //实现首次自动显示加载提示
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        csdnItemAdapter = new CSDNItemAdapter(mActivity, new ArrayList<CSDNData>(), true);
        csdnItemAdapter.setLoadingView(R.layout.load_loading_layout);
        csdnItemAdapter.setOnItemClickListener(new OnItemClickListeners<CSDNData>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, CSDNData data, int position) {
                Intent intent = new Intent(mActivity,CSDNListActivity.class);
                intent.putExtra("csdn_item_data",data);
                startActivity(intent);
            }
        });

        csdnItemAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                if (PAGE_COUNT == mTempPageCount && !isReload) {
                    return;
                }
                isLoadMore = true;
                PAGE_COUNT = mTempPageCount;
                fetchData();
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(csdnItemAdapter);

    }

    @Override
    protected void initData() {
        if (getArguments() == null) {
            return;
        }
        mSubtype = getArguments().getString(SUB_TYPE);
    }

    @Override
    public void onSuccess(List<CSDNData> data) {
        Log.e("TAG--name", data.get(0).getName());
        Log.e("TAG--url ", data.get(0).getImgUrl());
        Log.e("TAG--sub", data.get(0).getSubtype());
        Log.e("TAG--a", data.get(0).getArticleCount());
        Log.e("TAG--r", data.get(0).getReadCount());
        Log.e("TAG--href", data.get(0).getHref());

        if (isLoadMore) {
            if (data.size() == 0) {
                csdnItemAdapter.setLoadEndView(R.layout.load_end_layout);
            } else {
                csdnItemAdapter.setLoadMoreData(data);
                mTempPageCount++;
            }
        } else {
            csdnItemAdapter.setNewData(data);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onError() {
        if (isLoadMore) {
            csdnItemAdapter.setLoadFailedView(R.layout.load_failed_layout);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        isLoadMore = false;
        PAGE_COUNT = 1;
        fetchData();
    }

}
