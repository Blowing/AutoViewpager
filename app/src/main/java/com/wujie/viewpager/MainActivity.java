package com.wujie.viewpager;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private AutoPlayViewPager autoPlayViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btn_scroll_change_left).setOnClickListener(this);
        findViewById(R.id.btn_scroll_change_right).setOnClickListener(this);
        findViewById(R.id.btn_scroll_next).setOnClickListener(this);
        findViewById(R.id.btn_scroll_previous).setOnClickListener(this);

        List<Integer> resIds = new ArrayList<>();
        resIds.add(R.mipmap.ic_launcher);
        resIds.add(R.mipmap.nohttp);
        resIds.add(R.mipmap.nohttp_delete);
        resIds.add(R.mipmap.nohttp_des);
        resIds.add(R.mipmap.nohttp_get);
        resIds.add(R.mipmap.nohttp_head);
        resIds.add(R.mipmap.nohttp_options);
        resIds.add(R.mipmap.nohttp_patch);
        resIds.add(R.mipmap.nohttp_post);
        resIds.add(R.mipmap.nohttp_put);
        resIds.add(R.mipmap.nohttp_trace);

        BannerAdapter bannerAdapter = new BannerAdapter(this);
        bannerAdapter.update(resIds);

        autoPlayViewPager = (AutoPlayViewPager) findViewById(R.id.view_pager);
        autoPlayViewPager.setAdapter(bannerAdapter);




        autoPlayViewPager.setDirection(AutoPlayViewPager.Direction.LEFT);// 设置播放方向
        autoPlayViewPager.setCurrentItem(200); // 设置每个Item展示的时间
        autoPlayViewPager.start();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scroll_previous:// 播放上一个
                autoPlayViewPager.previous();
                break;
            case R.id.btn_scroll_next:// 播放下一个
                autoPlayViewPager.next();
                break;
            case R.id.btn_scroll_change_left:// 改变为向左滑动
                autoPlayViewPager.setDirection(AutoPlayViewPager.Direction.LEFT);
                break;
            case R.id.btn_scroll_change_right:// 改变为向右滑动
                autoPlayViewPager.setDirection(AutoPlayViewPager.Direction.RIGHT);
                break;
        }
    }
}
