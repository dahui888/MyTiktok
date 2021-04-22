package com.bytedance.tiktok.fragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.WorkAdapter;
import com.bytedance.tiktok.base.BaseFragment;
import com.bytedance.tiktok.bean.DataCreate;

/**
 * create i小灰
 * create on 2020-05-19
 * description 个人作品fragment
 */
public class WorkFragment extends BaseFragment {
    RecyclerView recyclerView;
    private WorkAdapter workAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_work;
    }
    @Override
    protected void init(View v) {
        recyclerView = v.findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        workAdapter = new WorkAdapter(getActivity(), DataCreate.datas);
        recyclerView.setAdapter(workAdapter);
    }

}
