package com.item.status.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.item.status.R;
import com.item.status.util.StatusBarUtil;

public class OneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.bar_tool);
        // 状态栏透明和间距的处理
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, mToolbar);
         StatusBarUtil.darkMode(this, true);
    }
}
