package com.item.status.banner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.item.status.R;
import com.item.status.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {

//    public static final String[] IMAGES = new String[] {
//            "http://image.tianjimedia.com/uploadImages/2012/067/ORQR14KR5DDC.jpg",
//            "http://image.tianjimedia.com/uploadImages/2012/067/X6BEO07U962E.jpg",
//            "http://image.tianjimedia.com/uploadImages/2012/067/F9X84V2ST716.jpg",
//            "http://image.tianjimedia.com/uploadImages/2012/067/RY445ENQ16BH.jpg",
//            "http://image.tianjimedia.com/uploadImages/2012/067/74KAJLN0JL95.jpg",
//            "http://image.tianjimedia.com/uploadImages/2012/067/N80N0GUA36N0.jpg"
//
//    };
    private List<String> mImages = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        mImages.add( "http://image.tianjimedia.com/uploadImages/2012/067/ORQR14KR5DDC.jpg");
        mImages.add("http://image.tianjimedia.com/uploadImages/2012/067/X6BEO07U962E.jpg");
        mImages.add("http://image.tianjimedia.com/uploadImages/2012/067/F9X84V2ST716.jpg");
        mImages.add("http://image.tianjimedia.com/uploadImages/2012/067/RY445ENQ16BH.jpg");
        mImages.add("http://image.tianjimedia.com/uploadImages/2012/067/74KAJLN0JL95.jpg");
        mImages.add("http://image.tianjimedia.com/uploadImages/2012/067/N80N0GUA36N0.jpg");
        Banner banner = findViewById(R.id.banners);
        // 设置banner样式(默认为CIRCLE_INDICATOR)
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        // 设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        // 设置图片集合
        banner.setImages(mImages);
        // 设置banner动画效果
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        // 设置标题合集
       // banner.setBannerTitles(null);
        // 设置自动轮播(默认为true)
        banner.isAutoPlay(true);
        // 设置轮播时间
        banner.setDelayTime(5000);
        // 设置指示器位置
        banner.setIndicatorGravity(BannerConfig.CENTER);
        // banner设置方法全部调用完毕时 最后调用
        banner.start();
    }
}
