package com.item.status;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.item.status.adapter.OneAdapter;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private RecyclerView mRecycler;
    private OneAdapter oneAdapter;
    private View layoutStickHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mRecycler = findViewById(R.id.recyclerView);
        layoutStickHeader = findViewById(R.id.layout_stick_header);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        List<String> data = new ArrayList<>();
        for(int i=0;i < 20;i++){
            data.add("item " + i);
        }
        oneAdapter = new OneAdapter();
        oneAdapter.addData(data);
        mRecycler.setAdapter(oneAdapter);

        View header = LayoutInflater.from(this).inflate(R.layout.view_header_three,(ViewGroup)mRecycler.getParent(),false);
        initStickView(header);
    }
    private int top = -1;
    private int mScrollY = 0;
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
    private void addHeaderFilterClickListener(final View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecycler.smoothScrollBy(0, view.getTop() - mScrollY);
               // showFilter();
            }
        });
    }
    private void addScrollListener() {

        layoutStickHeader.setTranslationY(top);
        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
