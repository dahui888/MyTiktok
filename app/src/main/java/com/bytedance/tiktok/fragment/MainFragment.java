package com.bytedance.tiktok.fragment;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseFragment;
import com.bytedance.tiktok.base.CommPagerAdapter;
import com.bytedance.tiktok.bean.PauseVideoEvent;
import com.bytedance.tiktok.utils.RxBus;

import java.util.ArrayList;

/**
 * create i小灰
 * create on 2020-05-19
 * description 主页fragment
 */
public class MainFragment extends BaseFragment {
    private CurrentLocationFragment currentLocationFragment;
    private RecommendFragment recommendFragment;
    private ViewPager viewPager;
    private XTabLayout tabTitle;
    private XTabLayout tabMainMenu;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private CommPagerAdapter pagerAdapter;
    /** 默认显示第一页推荐页 */
    public static int curPage = 1;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init(View v) {

        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        tabTitle = (XTabLayout) v.findViewById(R.id.tab_title);
        tabMainMenu = (XTabLayout) v.findViewById(R.id.tab_mainmenu);

        setFragments();

        setMainMenu();
    }

    private void setFragments() {
        currentLocationFragment = new CurrentLocationFragment();
        recommendFragment = new RecommendFragment();
        fragments.add(currentLocationFragment);
        fragments.add(recommendFragment);

        tabTitle.addTab(tabTitle.newTab().setText("玄武区"));
        tabTitle.addTab(tabTitle.newTab().setText("推荐"));

        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, new String[] {"玄武区", "推荐"});
        viewPager.setAdapter(pagerAdapter);
        tabTitle.setupWithViewPager(viewPager);

        tabTitle.getTabAt(1).select();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                curPage = position;

                if (position == 1) {
                    //继续播放
                    RxBus.getDefault().post(new PauseVideoEvent(true));
                } else {
                    //切换到其他页面，需要暂停视频
                    RxBus.getDefault().post(new PauseVideoEvent(false));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setMainMenu() {
        tabMainMenu.addTab(tabMainMenu.newTab().setText("首页"));
        tabMainMenu.addTab(tabMainMenu.newTab().setText("朋友"));
        tabMainMenu.addTab(tabMainMenu.newTab().setText(""));
        tabMainMenu.addTab(tabMainMenu.newTab().setText("消息"));
        tabMainMenu.addTab(tabMainMenu.newTab().setText("我"));
    }

}
