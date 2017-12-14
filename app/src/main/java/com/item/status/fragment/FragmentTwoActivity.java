package com.item.status.fragment;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.item.status.R;
import com.item.status.activity.BaseActivity;
import com.item.status.fragment.two.CategoryTwoFragment;
import com.item.status.fragment.two.HomeTwoFragment;
import com.item.status.fragment.two.ServiceTwoFragment;

public class FragmentTwoActivity extends BaseActivity implements View.OnClickListener {

   // private FrameLayout content;
    private LinearLayout ll_home;
    private LinearLayout ll_service;
    private LinearLayout ll_category;
    private HomeTwoFragment homeTwoFragment;
    private CategoryTwoFragment categoryTwoFragment;
    private ServiceTwoFragment serviceTwoFragment;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_fragment_two;
    }

    @Override
    protected void initView() {
        super.initView();
      //  content = findViewById(R.id.content);
        ll_home = findViewById(R.id.ll_home);
        ll_category = findViewById(R.id.ll_category);
        ll_service = findViewById(R.id.ll_service);
        selectedFragment(0);
        tabSelected(ll_home);
    }

    @Override
    protected void initListener() {
        super.initListener();
        ll_home.setOnClickListener(this);
        ll_category.setOnClickListener(this);
        ll_service.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                selectedFragment(0);
                tabSelected(ll_home);
                break;
            case R.id.ll_category:
                selectedFragment(1);
                tabSelected(ll_category);
                break;
            case R.id.ll_service:
                selectedFragment(2);
                tabSelected(ll_service);
                break;
        }
    }

    private void selectedFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (homeTwoFragment == null) {
                    homeTwoFragment = new HomeTwoFragment();
                    transaction.add(R.id.content, homeTwoFragment);
                } else
                    transaction.show(homeTwoFragment);
                break;
            case 1:
                if (categoryTwoFragment == null) {
                    categoryTwoFragment = new CategoryTwoFragment();
                    transaction.add(R.id.content, categoryTwoFragment);
                } else
                    transaction.show(categoryTwoFragment);
                break;
            case 2:
                if (serviceTwoFragment == null) {
                    serviceTwoFragment = new ServiceTwoFragment();
                    transaction.add(R.id.content, serviceTwoFragment);
                } else
                    transaction.show(serviceTwoFragment);
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (homeTwoFragment != null)
            transaction.hide(homeTwoFragment);
        if (categoryTwoFragment != null)
            transaction.hide(categoryTwoFragment);
        if (serviceTwoFragment != null)
            transaction.hide(serviceTwoFragment);

    }

    private void tabSelected(LinearLayout linearLayout) {
        ll_home.setSelected(false);
        ll_category.setSelected(false);
        ll_service.setSelected(false);
        linearLayout.setSelected(true);
    }
}
