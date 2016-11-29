package com.wildwolf.mycsdn.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.wildwolf.mycsdn.App;
import com.wildwolf.mycsdn.R;
import com.wildwolf.mycsdn.data.BlogData;
import com.wildwolf.mycsdn.utils.ImageLoader;

import butterknife.Bind;

/**
 * Created by ${wild00wolf} on 2016/11/22.
 */
public class BlogDetailActivity extends BaseActivity {

    private BlogData blogData;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private String id;
    @Bind(R.id.web_view)
    WebView mWebView;
    @Bind(R.id.iv_web_img)
    ImageView webImg;
    @Bind(R.id.tv_img_title)
    TextView imgTitle;
    @Bind(R.id.tv_img_source)
    TextView imgSource;

    @Bind(R.id.gank_detail_progress)
    ProgressBar mProgressBar;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_blog_detail;
    }

    @Override
    protected void initView() {
        initToolbar();
        initWebView();
    }

    @Override
    protected void initData() {
        blogData = getIntent().getParcelableExtra("blog_item_data");
    }

    private void initWebView() {
        final WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setBlockNetworkImage(true);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                mLoading.hide();
                settings.setBlockNetworkImage(false);
            }
        });
        mWebView.loadUrl(blogData.getUrl());

    }

    private void initToolbar() {
        String desc = blogData.getTitle();
        desc = desc.length() > 10 ? desc.substring(0, 10) + "..." : desc;
        toolbar.setTitle(desc);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        imgTitle.setText(blogData.getTitle());
        imgSource.setText("来自:  "+blogData.getUrl());

        ImageLoader.load(App.getContext(), blogData.getUrl(), webImg);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        mWebView.destroy();
        super.onDestroy();
    }
}

