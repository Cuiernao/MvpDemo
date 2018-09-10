package com.njsoft.retrofittest.mvp.presenter;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.njsoft.retrofittest.bean.ArticleBean;
import com.njsoft.retrofittest.bean.BannerBean;
import com.njsoft.retrofittest.mvp.ModelListener;
import com.njsoft.retrofittest.mvp.contract.MainContract;
import com.njsoft.retrofittest.mvp.model.MainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Cuiernao
 * date:   On 2018/9/7
 */
public class MainPresenter implements MainContract.Presenter {
    private MainModel mainModel;
    private MainContract.View view;

    private int mCourrentIndex=0;
    public MainPresenter(MainContract.View view) {
        this.view = view;
        mainModel = new MainModel();
    }

    @Override

    public void getBanerDate() {

        if (view != null) {
            mainModel.getBaner(new ModelListener<BannerBean>() {
                @Override
                public void modelSuccessful(BannerBean androidbean) {
                    if (androidbean.getErrorCode() == 0) {
                        List<BannerBean.DataBean> dataBeanList = androidbean.getData();
                        List<String> imgSrc = new ArrayList<>();
                        for (BannerBean.DataBean dataBean : dataBeanList) {
                            imgSrc.add(dataBean.getImagePath());
                        }
                        view.initBaner(imgSrc);
                    }
                }

                @Override
                public void modelErro(String error) {
                    view.showError(error);
                }
            });
        }
    }

    @Override
    public void getArticleDate() {
        mainModel.getArticleDate(new ModelListener<ArticleBean>() {
            @Override
            public void modelSuccessful(ArticleBean androidbean) {
                view.initRecyclerView(androidbean.getData().getDatas());
            }

            @Override
            public void modelErro(String error) {
                view.showError(error);
            }
        }, mCourrentIndex);
    }

    @Override
    public void getMoreArticle() {
        mainModel.getArticleDate(new ModelListener<ArticleBean>() {
            @Override
            public void modelSuccessful(ArticleBean androidbean) {
                view.loadMoreArticle(androidbean.getData().getDatas());
            }

            @Override
            public void modelErro(String error) {
                view.loadMoreArticleFail();
            }
        }, mCourrentIndex++);
    }
}
