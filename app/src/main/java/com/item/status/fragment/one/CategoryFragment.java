package com.item.status.fragment.one;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.item.status.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseLazyFragment {

    private Toolbar toolbar;
    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initView(View view) {
      toolbar=  mRootView.findViewById(R.id.toolbar);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true).init();
    }

    @Override
    protected void initData() {
        super.initData();
        Log.d("jiejie","-----------category initData");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.setTitleBar(getActivity(),toolbar);
        Log.d("jiejie","category----onViewCreate");
    }
}
