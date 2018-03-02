package com.hengyi.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hengyi.mvp.R;
import com.hengyi.mvp.bean.FreshNewsBean;
import com.hengyi.mvp.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by honghengqiang on 2018/2/21.
 */

public class FreshNewsAdapter extends BaseQuickAdapter<FreshNewsBean.PostsBean,BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener{

    private Context mContext;

    public FreshNewsAdapter(@Nullable List<FreshNewsBean.PostsBean> data) {
        super(R.layout.item_freshnews, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, FreshNewsBean.PostsBean postsBean) {
        mContext = viewHolder.itemView.getContext();
        viewHolder.setText(R.id.tv_title, postsBean.getTitle());
        viewHolder.setText(R.id.tv_info, postsBean.getAuthor().getName());
        viewHolder.setText(R.id.tv_commnetsize, postsBean.getComment_count() + "评论");
        ImageLoaderUtil.LoadImage(mContext, postsBean.getCustom_fields().getThumb_c().get(0), (ImageView) viewHolder.getView(R.id.iv_logo));
        setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Log.d("TAG", "onItemClick: "+position);
    }
}
