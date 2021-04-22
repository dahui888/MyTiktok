package com.bytedance.tiktok.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.activity.FocusActivity;
import com.bytedance.tiktok.activity.ShowImageActivity;
import com.bytedance.tiktok.base.BaseFragment;
import com.bytedance.tiktok.base.CommPagerAdapter;
import com.bytedance.tiktok.bean.CurUserBean;
import com.bytedance.tiktok.bean.MainPageChangeEvent;
import com.bytedance.tiktok.bean.VideoBean;
import com.bytedance.tiktok.utils.NumUtils;
import com.bytedance.tiktok.utils.RxBus;
import com.bytedance.tiktok.view.CircleImageView;
import com.bytedance.tiktok.view.IconFontTextView;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

import rx.Subscription;
import rx.functions.Action1;

/**
 * create i小灰
 * create on 2020-05-19
 * description 个人主页fragment
 */
public class PersonalHomeFragment extends BaseFragment implements View.OnClickListener {
    private ImageView ivBg;
    private CircleImageView ivHead;
    private RelativeLayout rlDropdown;
    private LinearLayout llFocus;
    private LinearLayout llFans;
    private ImageView ivReturn;
    private TextView tvTitle;
    private TextView tvFocus;
    private IconFontTextView ivMore;
    private Toolbar toolbar;
    private XTabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private TextView tvNickname;
    private TextView tvSign;
    private TextView tvGetLikeCount;
    private TextView tvFocusCount;
    private TextView tvFansCount;
    private TextView tvAddfocus;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private CommPagerAdapter pagerAdapter;
    private VideoBean.UserBean curUserBean;
    private Subscription subscription;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_personal_home;
    }

    @Override
    protected void init(View v) {
        appBarLayout = (AppBarLayout) v.findViewById(R.id.appbarlayout);
        ivBg = (ImageView) v.findViewById(R.id.iv_bg);
        ivHead = (CircleImageView) v.findViewById(R.id.iv_head);
        tvAddfocus = (TextView) v.findViewById(R.id.tv_addfocus);
        rlDropdown = (RelativeLayout) v.findViewById(R.id.rl_dropdown);
        tvNickname = (TextView) v.findViewById(R.id.tv_nickname);
        tvSign = (TextView) v.findViewById(R.id.tv_sign);
        tvGetLikeCount = (TextView) v.findViewById(R.id.tv_getlike_count);
        llFocus = (LinearLayout) v.findViewById(R.id.ll_focus);
        tvFocusCount = (TextView) v.findViewById(R.id.tv_focus_count);
        llFans = (LinearLayout) v.findViewById(R.id.ll_fans);
        tvFansCount = (TextView) v.findViewById(R.id.tv_fans_count);
        toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        ivReturn = (ImageView) v.findViewById(R.id.iv_return);
        tvTitle = (TextView) v.findViewById(R.id.tv_title);
        tvFocus = (TextView) v.findViewById(R.id.tv_focus);
        ivMore = (IconFontTextView) v.findViewById(R.id.iv_more);
        tabLayout = (XTabLayout) v.findViewById(R.id.tablayout);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        //解决toolbar左边距问题
        toolbar.setContentInsetsAbsolute(0, 0);

        setAppbarLayoutPercent();

        ivReturn.setOnClickListener(this);
        ivHead.setOnClickListener(this);
        ivBg.setOnClickListener(this);
        llFocus.setOnClickListener(this);
        llFans.setOnClickListener(this);

        setUserInfo();
    }

    /**
     * 过渡动画跳转页面
     *
     * @param view
     */
    public void transitionAnim(View view, int res) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, getString(R.string.trans));
        Intent intent = new Intent(getActivity(), ShowImageActivity.class);
        intent.putExtra("res", res);
        ActivityCompat.startActivity(getActivity(), intent, compat.toBundle());
    }

    public void setUserInfo() {
        subscription = RxBus.getDefault().toObservable(CurUserBean.class).subscribe((Action1<CurUserBean>) curUserBean -> {

            coordinatorLayoutBackTop();

            this.curUserBean = curUserBean.getUserBean();

            ivBg.setImageResource(curUserBean.getUserBean().getHead());
            ivHead.setImageResource(curUserBean.getUserBean().getHead());
            tvNickname.setText(curUserBean.getUserBean().getNickName());
            tvSign.setText(curUserBean.getUserBean().getSign());
            tvTitle.setText(curUserBean.getUserBean().getNickName());

            String subCount = NumUtils.numberFilter(curUserBean.getUserBean().getSubCount());
            String focusCount = NumUtils.numberFilter(curUserBean.getUserBean().getFocusCount());
            String fansCount = NumUtils.numberFilter(curUserBean.getUserBean().getFansCount());

            //获赞 关注 粉丝
            tvGetLikeCount.setText(subCount);
            tvFocusCount.setText(focusCount);
            tvFansCount.setText(fansCount);

            //关注状态
            if (curUserBean.getUserBean().isFocused()) {
                tvAddfocus.setText("已关注");
                tvAddfocus.setBackgroundResource(R.drawable.shape_round_halfwhite);
            } else {
                tvAddfocus.setText("关注");
                tvAddfocus.setBackgroundResource(R.drawable.shape_round_red);
            }

            setTabLayout();
        });
    }

    private void setTabLayout() {
        String[] titles = new String[]{"作品 " + curUserBean.getWorkCount(), "动态 " + curUserBean.getDynamicCount(), "喜欢 " + curUserBean.getLikeCount()};

        fragments.clear();
        for (int i = 0; i < titles.length; i++) {
            fragments.add(new WorkFragment());
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }

        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 根据滚动比例渐变view
     */
    private void setAppbarLayoutPercent() {
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            float percent = (Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange());  //滑动比例

            if (percent > 0.8) {
                tvTitle.setVisibility(View.VISIBLE);
                tvFocus.setVisibility(View.VISIBLE);

                float alpha = 1 - (1 - percent) * 5;  //渐变变换
                tvTitle.setAlpha(alpha);
                tvFocus.setAlpha(alpha);
            } else {
                tvTitle.setVisibility(View.GONE);
                tvFocus.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 自动回顶部
     */
    private void coordinatorLayoutBackTop() {
        CoordinatorLayout.Behavior behavior =
                ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout.Behavior) {
            AppBarLayout.Behavior appBarLayoutBehavior = (AppBarLayout.Behavior) behavior;
            int topAndBottomOffset = appBarLayoutBehavior.getTopAndBottomOffset();
            if (topAndBottomOffset != 0) {
                appBarLayoutBehavior.setTopAndBottomOffset(0);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_return) {
            RxBus.getDefault().post(new MainPageChangeEvent(0));
        }
        if (v.getId() == R.id.iv_head) {
            transitionAnim(ivHead, curUserBean.getHead());
        }
        if (v.getId() == R.id.iv_bg) {
        }
        if (v.getId() == R.id.ll_focus) {
            startActivity(new Intent(getActivity(), FocusActivity.class));
        }
        if (v.getId() == R.id.ll_fans) {
            startActivity(new Intent(getActivity(), FocusActivity.class));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}
