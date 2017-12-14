package com.item.status.fragment.one;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.item.status.R;
import com.item.status.adapter.OneAdapter;
import com.item.status.util.GlideImageLoader;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeOneFragment extends BaseLazyFragment {

    private Toolbar mToolbar;
    private RecyclerView mRv;
    private TwinklingRefreshLayout refreshLayout;
    private OneAdapter mOneAdapter;
    private List<String> mItemList = new ArrayList<>();
    private List<String> mImages = new ArrayList<>();
    private int bannerHeight;
    private View headView;


    @Override
    protected void initView(View view) {
        super.initView(view);
        mToolbar = mRootView.findViewById(R.id.toolbar);
        mRv = mRootView.findViewById(R.id.rv);
        refreshLayout = mRootView.findViewById(R.id.refreshLayout);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home_one;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.setTitleBar(getActivity(),mToolbar);
    }

    @Override
    protected void setView() {
        super.setView();
        refreshLayout.setEnableLoadmore(false);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);
        mOneAdapter = new OneAdapter();
        mOneAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT); // 设置动画
        mRv.setAdapter(mOneAdapter);
        addHeaderView();
        mOneAdapter.setPreLoadNumber(1);
        mOneAdapter.setNewData(mItemList);
    }
    private void addHeaderView(){
        if(mImages != null && mImages.size() >0){
            headView = LayoutInflater.from(mActivity).inflate(R.layout.item_banner,(ViewGroup)mRv.getParent(),false);
            Banner banner = (Banner)headView.findViewById(R.id.banner);
            banner.setImages(mImages)
                    .setImageLoader(new GlideImageLoader())
                    .setDelayTime(5000)
                    .start();
            mOneAdapter.addHeaderView(headView);
            ViewGroup.LayoutParams bannerParams = banner.getLayoutParams();
            ViewGroup.LayoutParams titleBarParams = mToolbar.getLayoutParams();
            bannerHeight = bannerParams.height - titleBarParams.height - ImmersionBar.getStatusBarHeight(getActivity());
        }
    }

    @Override
    protected void setListener() {
        super.setListener();
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                if(totalDy <= bannerHeight){
                    float alpha = (float)totalDy / bannerHeight;
                    mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT,
                            ContextCompat.getColor(mActivity,R.color.colorPrimary),alpha));
                }else {
                    mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                            , ContextCompat.getColor(mActivity, R.color.colorPrimary), 1));
                }
            }
        });
//        mOneAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mOneAdapter.addData(addData());
//                        if(mItemList.size() == 100){
//                            mOneAdapter.loadMoreEnd();
//                        }else {
//                            mOneAdapter.loadMoreComplete();
//                        }
//                    }
//                },2000);
//            }
//        });
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mItemList.clear();
                        mItemList.addAll(newData());
                        mOneAdapter.setNewData(mItemList);
                        refreshLayout.finishRefreshing();
                        mToolbar.setVisibility(View.VISIBLE);
                    }
                },2000);
            }

            @Override
            public void onPullingDown(TwinklingRefreshLayout refreshLayout, float fraction) {
                super.onPullingDown(refreshLayout, fraction);
                mToolbar.setVisibility(View.GONE);
            }

            @Override
            public void onPullDownReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
                super.onPullDownReleasing(refreshLayout, fraction);
                if(Math.abs(fraction - 1.0) > 0){
                    mToolbar.setVisibility(View.VISIBLE);
                }else {
                    mToolbar.setVisibility(View.GONE);
                }
            }
        });
    }
    private List<String> addData() {
        List<String> data = new ArrayList<>();
        for (int i = mItemList.size() + 1; i <= mItemList.size() + 20; i++) {
            data.add("item" + i);
        }
        return data;
    }

    private List<String> newData() {
        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            data.add("item" + i);
        }
        return data;
    }

    @Override
    protected void initData() {
        super.initData();
        for (int i = 1 ; i <= 20 ; i++){
            mItemList.add("item " + i);
        }
        mImages.add("http://image.tianjimedia.com/uploadImages/2012/067/X6BEO07U962E.jpg");
        mImages.add("http://image.tianjimedia.com/uploadImages/2012/067/F9X84V2ST716.jpg");
        mImages.add("http://image.tianjimedia.com/uploadImages/2012/067/N80N0GUA36N0.jpg");
        mImages.add("http://desk.zol.com.cn/showpic/1024x768_63850_14.html");
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarColorTransformEnable(false)
                .init();
    }
}
