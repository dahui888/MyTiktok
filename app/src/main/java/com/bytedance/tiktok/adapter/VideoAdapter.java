package com.bytedance.tiktok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseRvAdapter;
import com.bytedance.tiktok.base.BaseRvViewHolder;
import com.bytedance.tiktok.bean.VideoBean;
import com.bytedance.tiktok.view.ControllerView;
import com.bytedance.tiktok.view.LikeView;

import java.util.List;

/**
 * create i小灰
 * create on 2020-05-20
 * description
 */
public class VideoAdapter extends BaseRvAdapter<VideoBean, VideoAdapter.VideoViewHolder> {

    public VideoAdapter(Context context, List<VideoBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(VideoViewHolder holder, VideoBean videoBean, int position) {
        holder.controllerView.setVideoData(videoBean);

        holder.ivCover.setImageResource(videoBean.getCoverRes());

        holder.likeView.setOnLikeListener(() -> {
            if (!videoBean.isLiked()) {  //未点赞，会有点赞效果，否则无
                holder.controllerView.like();
            }

        });
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    public class VideoViewHolder extends BaseRvViewHolder {
        ImageView ivCover;
        LikeView likeView;
        ControllerView controllerView;

        public VideoViewHolder(View itemView) {
            super(itemView);
            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
            likeView = (LikeView) itemView.findViewById(R.id.likeview);
            controllerView = (ControllerView) itemView.findViewById(R.id.controller);
        }
    }
}
