package com.item.status.fragment.three;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.item.status.R;

/**
 * Created by Jie on 2017/12/14.
 */

public class ServiceThreeFragment extends BaseThreeFragment{


    private Toolbar toolbar;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_category_three;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.setTitleBar(getActivity(), toolbar);
    }

    @Override
    protected void setView(View mRootView) {
        super.setView(mRootView);
        toolbar = mRootView.findViewById(R.id.toolbar);
    }
}
