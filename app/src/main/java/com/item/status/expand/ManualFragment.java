package com.item.status.expand;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.item.status.R;

import net.cachapa.expandablelayout.ExpandableLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManualFragment extends Fragment implements SeekBar.OnSeekBarChangeListener, ExpandableLayout.OnExpansionUpdateListener {

    private SeekBar seekBar;
    private ExpandableLayout expandableLayout;
    private View content;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_manual, container, false);
        // Inflate the layout for this fragment
        seekBar = rootView.findViewById(R.id.seek_bar);
        expandableLayout = rootView.findViewById(R.id.expandable_layout);
        content = rootView.findViewById(R.id.content);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        seekBar.setOnSeekBarChangeListener(this);
        expandableLayout.setOnExpansionUpdateListener(this);
    }
    @Override
    public void onExpansionUpdate(float expansionFraction, int state) {
        content.setAlpha(expansionFraction);
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        expandableLayout.setExpansion(seekBar.getProgress() / (float)seekBar.getMax());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


}
