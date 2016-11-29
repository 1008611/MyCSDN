package com.wildwolf.mycsdn.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.wildwolf.mycsdn.R;
import com.wildwolf.mycsdn.data.BlogData;
import com.wildwolf.mycsdn.data.CSDNData;
import com.wildwolf.mycsdn.parsenter.CsdnDPresenter;
import com.wildwolf.mycsdn.ui.adapter.CsdaDAdapter;
import com.wildwolf.mycsdn.ui.adapter.baseadapter.OnItemClickListeners;
import com.wildwolf.mycsdn.ui.adapter.baseadapter.OnLoadMoreListener;
import com.wildwolf.mycsdn.ui.adapter.baseadapter.ViewHolder;
import com.wildwolf.mycsdn.ui.view.CsdnDView;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by ${wild00wolf} on 2016/11/24.
 */
public class CSDNListActivity extends BaseMvpActivity<CsdnDView, CsdnDPresenter> implements CsdnDView, SwipeRefreshLayout.OnRefreshListener {

    private int PAGE_COUNT = 1;
    private String mSubtype;
    private int mTempPageCount = 2;

    private CSDNData csdnData;
    private CsdaDAdapter csdaDAdapter;

    private boolean isLoadMore;

    @Bind(R.id.girl_detail_toolbar)
    Toolbar mToolbar;

    @Bind(R.id.type_item_recyclerview)
    RecyclerView mRecyclerView;

    @Bind(R.id.type_item_swipfreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected CsdnDPresenter initPresenter() {
        return new CsdnDPresenter();
    }

    @Override
    protected void fetchData() {
        mPresenter.getCsdnData(mSubtype, PAGE_COUNT);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.csdn_list_layout;
    }

    @Override
    protected void initView() {
        initToolbar();
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //实现首次自动显示加载提示
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        csdaDAdapter = new CsdaDAdapter(mContext, new ArrayList<BlogData>(), true);
        csdaDAdapter.setLoadingView(R.layout.load_loading_layout);
        csdaDAdapter.setOnItemClickListener(new OnItemClickListeners<BlogData>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, BlogData data, int position) {
                Intent intent = new Intent(mContext,BlogDetailActivity.class);
                intent.putExtra("blog_item_data",data);
                startActivity(intent);
            }
        });

        csdaDAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
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

        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(csdaDAdapter);
    }

    private void initToolbar() {
        //setTitle方法要在setSupportActionBar(toolbar)之前调用，否则不起作用
        String title = csdnData.getName();

        mToolbar.setTitle(title);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        csdnData = getIntent().getParcelableExtra("csdn_item_data");
        mSubtype = csdnData.getHref();
        Log.e("TAG------", "");
    }

    @Override
    public void onSuccess(List<BlogData> data) {


        if (isLoadMore) {
            if (data.size() == 0) {
                csdaDAdapter.setLoadEndView(R.layout.load_end_layout);
            } else {
                csdaDAdapter.setLoadMoreData(data);
                mTempPageCount++;
            }
        } else {
            csdaDAdapter.setNewData(data);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onError() {
        if (isLoadMore) {
            csdaDAdapter.setLoadFailedView(R.layout.load_failed_layout);
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
