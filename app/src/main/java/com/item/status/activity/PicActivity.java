package com.item.status.activity;


import com.item.status.R;

public class PicActivity extends BaseActivity {


    @Override
    protected int setLayoutId() {
        return R.layout.activity_pic;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.transparentBar().init();
    }
}
