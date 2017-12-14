package com.item.status.fragment.one;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.item.status.R;

/**
 * Created by Jie on 2017/12/14.
 */

public class ServiceOneFragment extends BaseLazyFragment{
    private Toolbar toolbar;
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_one_service;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // Log.d("jiejie","----------");
        ImmersionBar.setTitleBar(getActivity(),toolbar);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
       toolbar= mRootView.findViewById(R.id.toolbar);
    }

    @Override
    protected void initData() {
        super.initData();
       // Log.d("jiejie","----------Service");
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).navigationBarColor(R.color.colorAccent).init();
    }
}
