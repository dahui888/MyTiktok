package com.bytedance.tiktok.fragment;

import android.os.CountDownTimer;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.GridVideoAdapter;
import com.bytedance.tiktok.base.BaseFragment;
import com.bytedance.tiktok.bean.DataCreate;

/**
 * create i小灰
 * create on 2020-05-19
 * description 附近的人fragment
 */
public class CurrentLocationFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    private GridVideoAdapter adapter;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_current_location;
    }

    @Override
    protected void init(View v) {
        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.refreshlayout);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);


        new DataCreate().initData();

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        adapter = new GridVideoAdapter(getActivity(), DataCreate.datas);
        recyclerView.setAdapter(adapter);



        refreshLayout.setColorSchemeResources(R.color.color_link);
        refreshLayout.setOnRefreshListener(() -> new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                refreshLayout.setRefreshing(false);
            }
        }.start());
    }

}
