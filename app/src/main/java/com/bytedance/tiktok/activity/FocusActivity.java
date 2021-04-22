package com.bytedance.tiktok.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseActivity;
import com.bytedance.tiktok.base.CommPagerAdapter;
import com.bytedance.tiktok.fragment.FansFragment;

import java.util.ArrayList;

/**
 * create i小灰
 * create on 2020-05-14
 * description 粉丝关注人页面
 */
public class FocusActivity extends BaseActivity {
    private XTabLayout tabLayout;
    private ViewPager viewPager;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private CommPagerAdapter pagerAdapter;
    private String[] titles = new String[] {"关注 128", "粉丝 128", "推荐关注"};


    @Override
    protected int setLayoutId() {
        return R.layout.activity_focus;
    }

    @Override
    protected void init() {
        tabLayout = (XTabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);


        for (int i=0;i<titles.length;i++) {
            fragments.add(new FansFragment());
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }

        pagerAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView() {

    }
}
