package com.wildwolf.mycsdn.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.wildwolf.mycsdn.R;
import com.wildwolf.mycsdn.ui.fragment.BaseFragment;
import com.wildwolf.mycsdn.ui.fragment.TypeFragment;
import com.wildwolf.mycsdn.utils.ResourceUtil;
import com.wildwolf.mycsdn.utils.SnackbarUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.nav_view)
    NavigationView navView;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    private String mCurrentType;
    private boolean isBackPressed;
    private Map<String, BaseFragment> mTypeFragments;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initStatusBar();
        initDrawer();

        replaceFragment(TypeFragment.newInstance(ResourceUtil.resToStr(mContext, R.string.csdn)));
    }

//    private void doReplace(String type) {
//        if (!type.equals(mCurrentType)) {
//            replaceFragment(TypeFragment.newInstance(type), type, mCurrentType);
//            mCurrentType = type;
//            Log.e("TAG-mCurrentType",type);
//        }
//    }
//
//    private void replaceFragment(TypeFragment fragment, String type, String mCurrentType) {
//        if (mTypeFragments.get(type) == null) {
//            mTypeFragments.put(type, fragment);
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.add(R.id.main_fragment_container, fragment, type)
//                    .commit();
//        }
//        if (mTypeFragments.get(mCurrentType) != null) {
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.hide(mTypeFragments.get(mCurrentType))
//                    .show(mTypeFragments.get(type))
//                    .commit();
//        }
//    }

    private void initDrawer() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        toolbar.setTitle(R.string.app_name);

        navView.setNavigationItemSelectedListener(this);
        navView.setCheckedItem(R.id.action_settings);
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //将侧边栏顶部延伸至status bar
            drawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar
            drawerLayout.setClipToPadding(false);
        }
    }

    @Override
    protected void initData() {
        mTypeFragments = new HashMap<>();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (isBackPressed) {
                super.onBackPressed();
                return;
            }
            isBackPressed = true;
            SnackbarUtil.show(drawerLayout, R.string.back_pressed_tip);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isBackPressed = false;
                }
            }, 2000);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Fragment fragment;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if  (id == R.id.nav_blog) {
//            doReplace(ResourceUtil.resToStr(mContext, R.string.blog));
            replaceFragment(TypeFragment.newInstance(ResourceUtil.resToStr(mContext, R.string.blog)));
        } else if (id == R.id.nav_csdn) {
//            doReplace(ResourceUtil.resToStr(mContext, R.string.csdn));
            replaceFragment(TypeFragment.newInstance(ResourceUtil.resToStr(mContext, R.string.csdn)));
        }  else if (id == R.id.nav_lib) {
//            doReplace(ResourceUtil.resToStr(mContext, R.string.lib));
            replaceFragment(TypeFragment.newInstance(ResourceUtil.resToStr(mContext, R.string.lib)));
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (fragment.isAdded()){
            fragmentTransaction.show(fragment);
        }else {
            fragmentTransaction.replace(R.id.main_fragment_container,fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
//        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, fragment).commit();
    }



}
