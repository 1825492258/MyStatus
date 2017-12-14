package com.item.status.activity;
import com.item.status.R;

public class ShapeActivity extends BaseActivity {


    @Override
    protected int setLayoutId() {
        return R.layout.activity_shape;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar).init();
    }
}
