package com.item.status.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.gyf.barlibrary.ImmersionBar;
import com.item.status.R;

public class WhiteStatusActivity extends AppCompatActivity {
    private ImmersionBar immersionBar;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_status);
        view = findViewById(R.id.top_view);
       immersionBar= ImmersionBar.with(this)
               .statusBarView(view)
               .statusBarDarkFont(true,0.2f);
       immersionBar.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        immersionBar.destroy();
    }
}
