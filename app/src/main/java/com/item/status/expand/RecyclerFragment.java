package com.item.status.expand;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.item.status.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recycler, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> jj = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            jj.add("--------ssss" + i);
        }
        SimpleAdapter adapter = new SimpleAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.addData(jj);
    }

    private class SimpleAdapter extends BaseQuickAdapter<String, SimpleAdapter.MyViewHolder> {
        private static final int UNSELECTED = -1;
        private int selectedItem = UNSELECTED;

        public SimpleAdapter() {
            super(R.layout.item_simple);
        }

        @Override
        protected void convert(MyViewHolder helper , String item) {
            Log.d("jiejie","---  " + item);
           //     helper.setText(R.id.item_view,item);
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.bind(position);
        }

        public class MyViewHolder extends BaseViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {
            private ExpandableLayout itemLayout;
            private TextView itemView;
            private int position;

            public MyViewHolder(View view) {
                super(view);
                itemLayout = view.findViewById(R.id.item_layout);
                itemView = view.findViewById(R.id.item_view);
                itemView.setOnClickListener(this);
                itemLayout.setOnExpansionUpdateListener(this);
            }
            public void bind(int position){
                this.position = position;
                itemView.setText("--------" + position);
                itemView.setSelected(false);
            }
            @Override
            public void onClick(View view) {
                MyViewHolder holder = (MyViewHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
                if (holder != null) {
                    holder.itemView.setSelected(false);
                    holder.itemLayout.collapse();
                }
                if (position == selectedItem) {
                    selectedItem = UNSELECTED;
                } else {
                    itemView.setSelected(true);
                    itemLayout.expand();
                    selectedItem = position;
                }
            }

            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                if (state != ExpandableLayout.State.COLLAPSED) {
                    recyclerView.smoothScrollToPosition(getAdapterPosition());
                }
            }
        }
    }

}

