package com.item.status.fragment.three;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.item.status.CustomViewPager;
import com.item.status.R;
import com.item.status.activity.BaseActivity;

import java.util.ArrayList;

public class FragmentThreeActivity extends BaseActivity implements View.OnClickListener {
    private CustomViewPager mViewPage;
    private LinearLayout ll_home;
    private LinearLayout ll_category;
    private LinearLayout ll_service;
    private ArrayList<Fragment> mFragments;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_fragment_three;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.keyboardEnable(true).init();
    }

    @Override
    protected void initView() {
        super.initView();
        mViewPage = findViewById(R.id.viewPager);
        ll_home = findViewById(R.id.ll_home);
        ll_category = findViewById(R.id.ll_category);
        ll_service = findViewById(R.id.ll_service);
    }

    @Override
    protected void initListener() {
        super.initListener();
        ll_home.setOnClickListener(this);
        ll_category.setOnClickListener(this);
        ll_service.setOnClickListener(this);
        mViewPage.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        tabSelected(ll_home);
                        mImmersionBar.statusBarDarkFont(false).init();
                        break;
                    case 1:
                        tabSelected(ll_category);
                        mImmersionBar.statusBarDarkFont(true).init();
                        break;
                    case 2:
                        tabSelected(ll_service);
                        mImmersionBar.statusBarDarkFont(false).init();
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        HomeThreeFragment homeThreeFragment = new HomeThreeFragment();
        CategoryThreeFragment categoryThreeFragment = new CategoryThreeFragment();
        ServiceThreeFragment serviceThreeFragment = new ServiceThreeFragment();
        mFragments.add(homeThreeFragment);
        mFragments.add(categoryThreeFragment);
        mFragments.add(serviceThreeFragment);

        mViewPage.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mViewPage.setOffscreenPageLimit(3);
        ll_home.setSelected(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_home:
                Log.d("jiejie","-----------点000");
                mViewPage.setCurrentItem(0);
                tabSelected(ll_home);
                break;
            case R.id.ll_category:
                mViewPage.setCurrentItem(1);
                tabSelected(ll_category);
                break;
            case R.id.ll_service:
                mViewPage.setCurrentItem(2);
                tabSelected(ll_service);
                break;
        }
    }

    private void tabSelected(LinearLayout linearLayout) {
        ll_home.setSelected(false);
        ll_category.setSelected(false);
        ll_service.setSelected(false);
        linearLayout.setSelected(true);
    }

    private class MyAdapter extends FragmentPagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
