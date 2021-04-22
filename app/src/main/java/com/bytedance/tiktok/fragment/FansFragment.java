package com.bytedance.tiktok.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.FansAdapter;
import com.bytedance.tiktok.base.BaseFragment;
import com.bytedance.tiktok.bean.DataCreate;

public class FansFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private FansAdapter fansAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_fans;
    }

    @Override
    protected void init(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fansAdapter = new FansAdapter(getContext(), DataCreate.userList);
        recyclerView.setAdapter(fansAdapter);
    }
}
