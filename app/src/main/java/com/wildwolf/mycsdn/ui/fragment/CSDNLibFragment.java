package com.wildwolf.mycsdn.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;


import com.wildwolf.mycsdn.R;
import com.wildwolf.mycsdn.data.CSDNLibData;
import com.wildwolf.mycsdn.parsenter.CSDNLibPresenter;
import com.wildwolf.mycsdn.ui.adapter.CSDNLibAdapter;
import com.wildwolf.mycsdn.ui.adapter.baseadapter.OnItemClickListeners;
import com.wildwolf.mycsdn.ui.adapter.baseadapter.OnLoadMoreListener;
import com.wildwolf.mycsdn.ui.adapter.baseadapter.ViewHolder;
import com.wildwolf.mycsdn.ui.view.CSDNLibView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by ${wild00wolf} on 2016/11/25.
 */
public class CSDNLibFragment extends BaseMvpFragment<CSDNLibView, CSDNLibPresenter> implements CSDNLibView, SwipeRefreshLayout.OnRefreshListener {

    private int PAGE_COUNT = 1;
    private String mSubtype;
    private String mType = "all";
    private int mTempPageCount = 2;

    private CSDNLibAdapter CSDNLibAdapter;

    private boolean isLoadMore;

    @Bind(R.id.type_item_recyclerview)
    RecyclerView mRecyclerView;

    @Bind(R.id.type_item_swipfreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static CSDNLibFragment newInstance(String subtype) {
        CSDNLibFragment fragment = new CSDNLibFragment();
        Bundle arguments = new Bundle();
        arguments.putString(SUB_TYPE, subtype);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    protected CSDNLibPresenter initPresenter() {
        return new CSDNLibPresenter();
    }

    @Override
    protected void fetchData() {
        mPresenter.getCSDNLibData(mSubtype, mType, PAGE_COUNT);
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

        CSDNLibAdapter = new CSDNLibAdapter(mActivity, new ArrayList<CSDNLibData>(), true);
        CSDNLibAdapter.setLoadingView(R.layout.load_loading_layout);
        CSDNLibAdapter.setOnItemClickListener(new OnItemClickListeners<CSDNLibData>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, CSDNLibData data, int position) {
//                Intent intent = new Intent(mActivity, CSDNListActivity.class);
//                intent.putExtra("csdn_item_data", data);
//                startActivity(intent);
            }
        });


        CSDNLibAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
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

        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//可防止Item切换
        mRecyclerView.setLayoutManager(layoutManager);

//        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(layoutManager);


        mRecyclerView.setAdapter(CSDNLibAdapter);

    }

    @Override
    protected void initData() {
        if (getArguments() == null) {
            return;
        }
        mSubtype = getArguments().getString(SUB_TYPE);
    }

    @Override
    public void onSuccess(List<CSDNLibData> data) {

        Log.e("TAG--size ", data.size() + "");

        if (isLoadMore) {
            if (data.size() == 0) {
                CSDNLibAdapter.setLoadEndView(R.layout.load_end_layout);
            } else {
                CSDNLibAdapter.setLoadMoreData(data);
                mTempPageCount++;
            }
        } else {
            CSDNLibAdapter.setNewData(data);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onError() {
        if (isLoadMore) {
            CSDNLibAdapter.setLoadFailedView(R.layout.load_failed_layout);
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

