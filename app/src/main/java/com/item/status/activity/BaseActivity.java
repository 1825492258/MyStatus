package com.item.status.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by wuzongjie on 2017/12/6.
 * 积累
 */

public abstract class BaseActivity extends AppCompatActivity{

    protected ImmersionBar mImmersionBar;
    //private InputMethodManager imm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        // 初始化沉浸式
        if(isImmersionBarEnabled()){
            initImmersionBar();
        }
        // 初始化View
        initView();
        initListener();
        initData();
    }
    protected abstract int setLayoutId();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mImmersionBar != null){
            mImmersionBar.destroy();
        }
    }

    protected void initData() {
    }

    protected void initView() {
    }

    protected void initListener() {
    }

    protected  void initImmersionBar() {
        // 在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }
}
