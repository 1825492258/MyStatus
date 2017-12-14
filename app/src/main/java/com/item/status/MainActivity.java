package com.item.status;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.item.status.activity.OneActivity;
import com.item.status.activity.PicActivity;
import com.item.status.activity.ShapeActivity;
import com.item.status.activity.WhiteStatusActivity;
import com.item.status.banner.BannerActivity;
import com.item.status.fragment.FragmentOneActivity;
import com.item.status.fragment.FragmentTwoActivity;
import com.item.status.fragment.three.FragmentThreeActivity;
import com.item.status.util.StatusBarUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.bar_tool);
        // 状态栏透明和间距的处理
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, mToolbar);
        // StatusBarUtil.darkMode(this, true);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_mian).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_banner).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_mian:
                startActivity(new Intent(this, OneActivity.class));
                break;
            case R.id.btn_one:
                startActivity(new Intent(this, ShapeActivity.class));
                break;
            case R.id.btn_two:
                startActivity(new Intent(this, PicActivity.class));
                break;
            case R.id.btn_three:
                startActivity(new Intent(this, WhiteStatusActivity.class));
                break;
            case R.id.btn_four:
                startActivity(new Intent(this, FragmentOneActivity.class));
                break;
            case R.id.btn_five:
                startActivity(new Intent(this, FragmentTwoActivity.class));
                break;
            case R.id.btn_six:
                startActivity(new Intent(this, FragmentThreeActivity.class));
                break;
            case R.id.btn_banner:
                startActivity(new Intent(this, BannerActivity.class));
                break;
        }
    }
}
