package com.item.status.fragment.two;


import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.item.status.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryTwoFragment extends BaseTwoFragment {
    private Toolbar toolbar;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_category_two;
    }

    @Override
    protected void setView(View mRootView) {
        super.setView(mRootView);
       toolbar =  mRootView.findViewById(R.id.toolbar);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(toolbar)
                .init();
    }
}
