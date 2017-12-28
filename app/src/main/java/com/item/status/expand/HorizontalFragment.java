package com.item.status.expand;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.item.status.R;

import net.cachapa.expandablelayout.ExpandableLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class HorizontalFragment extends Fragment implements ExpandableLayout.OnExpansionUpdateListener {



    private ImageView expandButton;
    private ExpandableLayout expandableLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView  = inflater.inflate(R.layout.fragment_horizontal,container,false);
        expandButton = rootView.findViewById(R.id.expand_button);
        expandableLayout = rootView.findViewById(R.id.expandable_layout);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        expandableLayout.setOnExpansionUpdateListener(this);
        expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLayout.toggle();
            }
        });
    }

    @Override
    public void onExpansionUpdate(float expansionFraction, int state) {
        expandButton.setRotation(expansionFraction * 180);
    }
}
