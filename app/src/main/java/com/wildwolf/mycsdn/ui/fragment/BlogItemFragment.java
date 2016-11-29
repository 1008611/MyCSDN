package com.wildwolf.mycsdn.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.wildwolf.mycsdn.R;
import com.wildwolf.mycsdn.data.BlogData;
import com.wildwolf.mycsdn.parsenter.BlogItemPresenter;
import com.wildwolf.mycsdn.ui.activity.BlogDetailActivity;
import com.wildwolf.mycsdn.ui.adapter.BlogItemAdapter;
import com.wildwolf.mycsdn.ui.adapter.baseadapter.OnItemClickListeners;
import com.wildwolf.mycsdn.ui.adapter.baseadapter.OnLoadMoreListener;
import com.wildwolf.mycsdn.ui.adapter.baseadapter.ViewHolder;
import com.wildwolf.mycsdn.ui.view.BlogItemView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by ${wild00wolf} on 2016/11/22.
 */
public class BlogItemFragment extends BaseMvpFragment<BlogItemView, BlogItemPresenter> implements BlogItemView, SwipeRefreshLayout.OnRefreshListener {

    private int PAGE_COUNT = 1;
    private String mSubtype;
    private int mTempPageCount = 2;
    private boolean isLoadMore;

    private BlogItemAdapter blogItemAdapter;

    @Bind(R.id.type_item_recyclerview)
    RecyclerView mRecyclerView;

    @Bind(R.id.type_item_swipfreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static BlogItemFragment newInstance(String subtype) {
        BlogItemFragment fragment = new BlogItemFragment();
        Bundle arguments = new Bundle();
        arguments.putString(SUB_TYPE, subtype);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    protected BlogItemPresenter initPresenter() {
        return new BlogItemPresenter();
    }

    @Override
    protected void fetchData() {
        mPresenter.getBlogItemData(mSubtype, PAGE_COUNT);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_type_item_layout;
    }

    @Override
    protected void initView() {

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //实现首次自动显示加载提示
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        blogItemAdapter = new BlogItemAdapter(mActivity, new ArrayList<BlogData>(), true);
        blogItemAdapter.setLoadingView(R.layout.load_loading_layout);


        blogItemAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
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

        blogItemAdapter.setOnItemClickListener(new OnItemClickListeners<BlogData>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, BlogData data, int position) {
                Intent intent = new Intent(getActivity(), BlogDetailActivity.class);
                intent.putExtra("blog_item_data", data);

                getActivity().startActivity(intent);
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(blogItemAdapter);

    }

    @Override
    protected void initData() {
        if (getArguments() == null) {
            return;
        }
        mSubtype = getArguments().getString(SUB_TYPE);
    }

    @Override
    public void onSuccess(List<BlogData> data) {

        Log.e("TAG--", data.get(0).getId());
        Log.e("TAG--url ", data.get(0).getUrl());

        if (isLoadMore) {
            if (data.size() == 0) {
                blogItemAdapter.setLoadEndView(R.layout.load_end_layout);
            } else {
                blogItemAdapter.setLoadMoreData(data);
                mTempPageCount++;
            }
        } else {
            blogItemAdapter.setNewData(data);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onError() {
        if (isLoadMore) {
            blogItemAdapter.setLoadFailedView(R.layout.load_failed_layout);
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

