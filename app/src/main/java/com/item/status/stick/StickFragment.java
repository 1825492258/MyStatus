package com.item.status.stick;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.item.status.R;
import com.item.status.adapter.OneAdapter;
import com.item.status.util.GlideImageLoader;
import com.youth.banner.Banner;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jie on 2017/12/14.
 * https://github.com/sungerk/elestickheader/blob/master/app/src/main/java/sunger/net/org/elestickheader/StickHeaderFragment.java
 */

public class StickFragment extends Fragment{
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewFilter;
    private View layoutStickHeader;
    private View layoutFilter;
    private ExpandableLayout expandableLayout;
    private OneAdapter oneAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int top = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stick,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerViewFilter = view.findViewById(R.id.recyclerView_filter);
        layoutStickHeader = view.findViewById(R.id.layout_stick_header);
        layoutFilter = view.findViewById(R.id.layout_filter);
        expandableLayout = view.findViewById(R.id.expandableLayout);
        initData();
    }
    private void initData() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewFilter.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(linearLayoutManager);
        oneAdapter = new OneAdapter();
        List<String> data = new ArrayList<>();
        for(int i=0;i < 20;i++){
            data.add("item " + i);
        }

        mImages.add( "http://image.tianjimedia.com/uploadImages/2012/067/ORQR14KR5DDC.jpg");
        mImages.add("http://image.tianjimedia.com/uploadImages/2012/067/X6BEO07U962E.jpg");
        mImages.add("http://image.tianjimedia.com/uploadImages/2012/067/F9X84V2ST716.jpg");
        mImages.add("http://image.tianjimedia.com/uploadImages/2012/067/RY445ENQ16BH.jpg");
        mImages.add("http://image.tianjimedia.com/uploadImages/2012/067/74KAJLN0JL95.jpg");
        mImages.add("http://image.tianjimedia.com/uploadImages/2012/067/N80N0GUA36N0.jpg");

        addHeaderOne();
        View header1 = LayoutInflater.from(getContext()).inflate(R.layout.view_header_banner,(ViewGroup)recyclerView.getParent(),false);
        oneAdapter.addHeaderView(header1);
        final View header = LayoutInflater.from(getContext()).inflate(R.layout.view_header_three,(ViewGroup)recyclerView.getParent(),false);
        initStickView(header);
        oneAdapter.addHeaderView(header);
        header.setBackgroundColor(Color.BLUE);

        oneAdapter.addData(data);
        recyclerView.setAdapter(oneAdapter);
        layoutFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLayout.collapse();
            }
        });
        expandableLayout.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                if(state == 0){
                    layoutFilter.setVisibility(View.GONE);
                }
            }
        });
        List<String> dataFilter = new ArrayList<>();
        dataFilter.add("综合排序");
        dataFilter.add("销量最高");
        dataFilter.add("起送价最低");
        dataFilter.add("配送最快");
        dataFilter.add("配送费最低");
        dataFilter.add("人均从低到高");
        dataFilter.add("人均从高到低");
        OneAdapter adapter = new OneAdapter();
        adapter.addData(dataFilter);
        recyclerViewFilter.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                expandableLayout.collapse();
            }
        });
    }

    private void initStickView(final View view) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                top = view.getTop();
                addHeaderFilterClickListener(view);
                addScrollListener();
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private List<String> mImages = new ArrayList<>();
    private void addHeaderOne(){

        if(mImages !=null && mImages.size() >0){
            View header1 = LayoutInflater.from(getContext()).inflate(R.layout.item_banner,(ViewGroup)recyclerView.getParent(),false);
            Banner banner = header1.findViewById(R.id.banner);
            banner.setImages(mImages)
                    .setImageLoader(new GlideImageLoader())
                    .setDelayTime(3000)
                    .start();
            oneAdapter.addHeaderView(header1);
        }
    }
    private void showFilter() {
        layoutFilter.postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutFilter.setVisibility(View.VISIBLE);
                expandableLayout.expand();
            }
        }, 300);


    }

    private int mScrollY = 0;

    private void addHeaderFilterClickListener(final View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.smoothScrollBy(0, view.getTop() - mScrollY);
                showFilter();
            }
        });
    }
    private void addScrollListener() {

        layoutStickHeader.setTranslationY(top);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy == 0) return;
                mScrollY += dy;
                int translationY = top - mScrollY;
                if (translationY < 0) translationY = 0;
                layoutStickHeader.setTranslationY(translationY);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }
}
