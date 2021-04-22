package com.bytedance.tiktok.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.PrivateLetterAdapter;
import com.bytedance.tiktok.adapter.ShareAdapter;
import com.bytedance.tiktok.bean.DataCreate;
import com.bytedance.tiktok.bean.ShareBean;

import java.util.ArrayList;

/**
 * create i小灰
 * create on 2020-05-25
 * description 分享弹框
 */
public class ShareDialog extends BaseBottomSheetDialog {
    private RecyclerView rvPrivateLetter;
    private RecyclerView rvShare;
    private TextView tvCancel;

    private PrivateLetterAdapter privateLetterAdapter;
    private ShareAdapter shareAdapter;
    private View view;
    private ArrayList<ShareBean> shareBeans = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_share, container);

        init(view);

        return view;
    }

    private void init(View v) {
        rvPrivateLetter = (RecyclerView) v.findViewById(R.id.rv_private_letter);
        rvShare = (RecyclerView) v.findViewById(R.id.rv_share);
        tvCancel = (TextView) v.findViewById(R.id.tv_cancel);

        rvPrivateLetter.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        privateLetterAdapter = new PrivateLetterAdapter(getContext(), DataCreate.userList);
        rvPrivateLetter.setAdapter(privateLetterAdapter);

        rvShare.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        shareAdapter = new ShareAdapter(getContext(), shareBeans);
        rvShare.setAdapter(shareAdapter);

        //关闭弹框
        tvCancel.setOnClickListener(v1 -> {
            dismiss();
        });

        setShareDatas();

    }

    private void setShareDatas() {
        shareBeans.add(new ShareBean(R.string.icon_friends, "朋友圈", R.color.color_wechat_iconbg));
        shareBeans.add(new ShareBean(R.string.icon_wechat, "微信", R.color.color_wechat_iconbg));
        shareBeans.add(new ShareBean(R.string.icon_qq, "QQ", R.color.color_qq_iconbg));
        shareBeans.add(new ShareBean(R.string.icon_qq_space, "QQ空间", R.color.color_qqzone_iconbg));
        shareBeans.add(new ShareBean(R.string.icon_weibo, "微博", R.color.color_weibo_iconbg));
        shareBeans.add(new ShareBean(R.string.icon_comment, "私信朋友", R.color.color_FF0041));

        shareAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getHeight() {
        return dp2px(getContext(), 355);
    }

}
