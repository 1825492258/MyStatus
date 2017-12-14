package com.item.status.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.item.status.CustomViewPager;
import com.item.status.R;
import com.item.status.activity.BaseActivity;
import com.item.status.fragment.one.CategoryFragment;
import com.item.status.fragment.one.HomeOneFragment;
import com.item.status.fragment.one.ServiceOneFragment;

import java.util.ArrayList;

public class FragmentOneActivity extends BaseActivity implements View.OnClickListener {

   private CustomViewPager mViewPage;
   private LinearLayout ll_home;
   private LinearLayout ll_category;
   private LinearLayout ll_service;
   private LinearLayout ll_mine;
   private ArrayList<Fragment> mFragments;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_fragment_one;
    }

    @Override
    protected void initView() {
        super.initView();
        mViewPage = (CustomViewPager)findViewById(R.id.viewPager);
        ll_home = findViewById(R.id.ll_home);
        ll_category = findViewById(R.id.ll_category);
        ll_service = findViewById(R.id.ll_service);
        ll_mine = findViewById(R.id.ll_mine);

    }

    @Override
    protected void initListener() {
        super.initListener();
        ll_home.setOnClickListener(this);
        ll_category.setOnClickListener(this);
        ll_service.setOnClickListener(this);
        ll_mine.setOnClickListener(this);
       mViewPage.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
           @Override
           public void onPageSelected(int position) {
               super.onPageSelected(position);
               switch (position){
                   case 0:
                       tabSelected(ll_home);
                       break;
                   case 1:
                       tabSelected(ll_category);
                       break;
                   case 2:
                       tabSelected(ll_service);
                       break;
                   case 3:
                       tabSelected(ll_mine);
                       break;
               }
           }
       });
    }

    @Override
    protected void initData() {
        super.initData();
        mFragments = new ArrayList<>();
        HomeOneFragment homeOneFragment = new HomeOneFragment();
        CategoryFragment categoryFragment = new CategoryFragment();
        ServiceOneFragment serviceOneFragment = new ServiceOneFragment();
        ServiceOneFragment serviceOneFragment1 = new ServiceOneFragment();
        mFragments.add(homeOneFragment);
        mFragments.add(categoryFragment);
        mFragments.add(serviceOneFragment);
        mFragments.add(serviceOneFragment1);
        mViewPage.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mViewPage.setOffscreenPageLimit(3);
        ll_home.setSelected(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_home:
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
            case R.id.ll_mine:
                mViewPage.setCurrentItem(3);
                tabSelected(ll_mine);
                break;
        }
    }

    private void tabSelected(LinearLayout linearLayout){
        ll_home.setSelected(false);
        ll_category.setSelected(false);
        ll_service.setSelected(false);
        ll_mine.setSelected(false);
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
