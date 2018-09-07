package com.njsoft.retrofittest.mvp.contract;

import com.njsoft.retrofittest.bean.ArticleBean;
import com.njsoft.retrofittest.bean.BannerBean;
import com.njsoft.retrofittest.mvp.ModelListener;

import java.util.List;

/**
 * author: Cuiernao
 * date:   On 2018/9/7
 */
public interface MainContract {
    interface Model {
        /**
         * 获取Baner数组
         *
         * @return 返回图片数组
         */
        void getBaner(ModelListener<BannerBean> listener);

        /**
         * 返回文章
         *
         * @return 返回文章
         */
        void getArticleDate(ModelListener<ArticleBean> listener, int page);
    }

    interface View {
        void showProgress();

        void hideProgress();

        /**
         * 初始化 Baner图片
         *
         * @param datas
         */
        void initBaner(List<String> datas);

        /**
         * 初始化文章列表
         *
         * @param datasBeanList
         */
        void initRecyclerView(List<ArticleBean.DataBean.DatasBean> datasBeanList);

        /**
         * 下拉加载更多文章
         */
        void loadMoreArticle(List<ArticleBean.DataBean.DatasBean> datasBeanList);

        void showError(String error);
    }

    interface Presenter {

        void getBanerDate();

        void getArticleDate();

        void getMoreArticle();
    }
}
