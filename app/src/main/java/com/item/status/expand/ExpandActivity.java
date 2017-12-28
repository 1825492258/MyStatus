package com.item.status.expand;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.item.status.R;

/**
 * https://github.com/cachapa/ExpandableLayout
 */
public class ExpandActivity extends AppCompatActivity {
    private static final String[] TAB_TITLES = {
            "Simple",
            "Accordion",
            "Recycler",
            "Horizontal",
            "Manual"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new SimpleAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class SimpleAdapter extends FragmentPagerAdapter {

        public SimpleAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SimpleFragment();
                case 1:
                    return new AccordionFragment();
                case 2:
                    return new RecyclerFragment();
                case 3:
                    return new HorizontalFragment();
                case 4:
                    return new ManualFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return TAB_TITLES.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return TAB_TITLES[position];
        }
    }
}
