package com.njsoft.retrofittest.mvp.model;

import com.njsoft.retrofittest.ApiManager;
import com.njsoft.retrofittest.bean.ArticleBean;
import com.njsoft.retrofittest.bean.BannerBean;
import com.njsoft.retrofittest.mvp.ModelListener;
import com.njsoft.retrofittest.mvp.contract.MainContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author: Cuiernao
 * date:   On 2018/9/7
 */
public class MainModel implements MainContract.Model {


    @Override
    public void getBaner(ModelListener<BannerBean> listener) {
        Call<BannerBean> call = ApiManager.getInstance().getApiService().getWanAndroidBraner();
        setMyCallback(listener, call);
    }

    @Override
    public void getArticleDate(ModelListener<ArticleBean> listener,int page) {
        Call<ArticleBean> call = ApiManager.getInstance().getApiService().getWanAndroidArticel(page);
        setMyCallback(listener, call);
    }
    private void setMyCallback(ModelListener listener, Call call) {
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    listener.modelSuccessful(response.body());
                } else {
                    listener.modelErro("获取异常");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                listener.modelErro("获取失败" + t.toString());
            }
        });
    }

}
