package com.bytedance.tiktok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseRvAdapter;
import com.bytedance.tiktok.base.BaseRvViewHolder;
import com.bytedance.tiktok.bean.ShareBean;
import com.bytedance.tiktok.view.IconFontTextView;

import java.util.List;

/**
 * create i小灰
 * create on 2020-05-25
 * description
 */
public class ShareAdapter extends BaseRvAdapter<ShareBean, ShareAdapter.ShareViewHolder> {

    public ShareAdapter(Context context, List<ShareBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(ShareViewHolder holder, ShareBean shareBean, int position) {
        holder.tvIcon.setText(shareBean.getIconRes());
        holder.tvText.setText(shareBean.getText());
        holder.viewBg.setBackgroundResource(shareBean.getBgRes());
    }

    @NonNull
    @Override
    public ShareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_share, parent, false);
        return new ShareViewHolder(view);
    }

    public class ShareViewHolder extends BaseRvViewHolder {
        TextView tvIcon;
        TextView tvText;
        View viewBg;

        public ShareViewHolder(View itemView) {
            super(itemView);

            viewBg = (View) itemView.findViewById(R.id.view_bg);
            tvIcon = (IconFontTextView) itemView.findViewById(R.id.tv_icon);
            tvText = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }
}
