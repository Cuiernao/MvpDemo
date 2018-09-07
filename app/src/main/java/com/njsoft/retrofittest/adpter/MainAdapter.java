package com.njsoft.retrofittest.adpter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.njsoft.retrofittest.R;
import com.njsoft.retrofittest.bean.ArticleBean;


import java.util.List;

/**
 * 主页适配器
 */
public class MainAdapter extends BaseQuickAdapter<ArticleBean.DataBean.DatasBean, BaseViewHolder> {

    public MainAdapter(int layoutResId, List<ArticleBean.DataBean.DatasBean> datasBeanList) {
        super(layoutResId, datasBeanList);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.DataBean.DatasBean item) {
        helper.setText(R.id.Article_Title, item.getTitle()+"")
                .setText(R.id.Article_Class, "作者："+item.getAuthor())
                .setText(R.id.Article_Url, item.getLink()+"");
    }
}