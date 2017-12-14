package com.item.status.fragment.two;


import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import com.item.status.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceTwoFragment extends BaseTwoFragment {

    private Toolbar toolbar;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_service_two;
    }

    @Override
    protected void setView(View mRootView) {
        super.setView(mRootView);
      toolbar=  mRootView.findViewById(R.id.toolbar);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("jiejie","--------------- Service");
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(toolbar).init();
    }
}
