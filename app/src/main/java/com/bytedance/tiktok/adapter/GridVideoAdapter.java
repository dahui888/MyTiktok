package com.bytedance.tiktok.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.activity.PlayListActivity;
import com.bytedance.tiktok.base.BaseRvAdapter;
import com.bytedance.tiktok.base.BaseRvViewHolder;
import com.bytedance.tiktok.bean.VideoBean;
import com.bytedance.tiktok.view.IconFontTextView;

import java.util.List;

/**
 * create i小灰
 * create on 2020-05-20
 * description
 */
public class GridVideoAdapter extends BaseRvAdapter<VideoBean, GridVideoAdapter.GridVideoViewHolder> {

    public GridVideoAdapter(Context context, List<VideoBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(GridVideoViewHolder holder, VideoBean videoBean, int position) {
        holder.ivCover.setBackgroundResource(videoBean.getCoverRes());
        holder.tvContent.setText(videoBean.getContent());
        holder.tvDistance.setText(videoBean.getDistance() + " km");
        holder.ivHead.setImageResource(videoBean.getUserBean().getHead());

        holder.itemView.setOnClickListener(v -> {
            PlayListActivity.initPos = position;
            context.startActivity(new Intent(context, PlayListActivity.class));
        });
    }

    @NonNull
    @Override
    public GridVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gridvideo, parent, false);
        return new GridVideoViewHolder(view);
    }

    public static class GridVideoViewHolder extends BaseRvViewHolder {
        ImageView ivCover;
        TextView tvContent;
        IconFontTextView tvDistance;
        ImageView ivHead;

        public GridVideoViewHolder(View itemView) {
            super(itemView);

            ivCover = itemView.findViewById(R.id.iv_cover);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvDistance = itemView.findViewById(R.id.tv_distance);
            ivHead = itemView.findViewById(R.id.iv_head);

        }
    }
}
