package com.njsoft.retrofittest;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

//import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.njsoft.retrofittest.adpter.MainAdapter;
import com.njsoft.retrofittest.basetop.BaseTopActivity;
import com.njsoft.retrofittest.bean.ArticleBean;
import com.njsoft.retrofittest.main.CustomLoadMoreView;
import com.njsoft.retrofittest.mvp.contract.MainContract;
import com.njsoft.retrofittest.mvp.presenter.MainPresenter;
import com.njsoft.retrofittest.utils.DialogProgressUtill;
import com.njsoft.retrofittest.utils.RGlideUtil;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 测试网络框架的用法
 */
public class MainActivity extends BaseTopActivity implements MainContract.View {

    @BindView(R.id.banner_guide_content)
    BGABanner bannerGuideContent;
    @BindView(R.id.ArticleList)
    RecyclerView mArticleList;
    private MainPresenter mainPresenter;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(this);
        mainPresenter.getBanerDate();
        mainPresenter.getArticleDate();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("WanAndroid逗比520", R.color.red);
        setToolBarColor(R.color.time_select);
    }

    @Override
    public void showProgress() {
        DialogProgressUtill.INSTANCE.init(this, "加载数据");
    }

    @Override
    public void hideProgress() {
        DialogProgressUtill.INSTANCE.dissDialog();

    }

    @Override
    public void initBaner(List<String> datas) {
        bannerGuideContent.setData(datas, Arrays.asList("", "", ""));
        bannerGuideContent.setAdapter((BGABanner.Adapter<ImageView, String>) (banner, itemView, model, position) ->
                RGlideUtil.setImageWithCache(MainActivity.this, model, itemView));
    }

    @Override
    public void initRecyclerView(List<ArticleBean.DataBean.DatasBean> datasBeanList) {
        adapter = new MainAdapter(R.layout.mian_item, datasBeanList);
        mArticleList.setLayoutManager(new LinearLayoutManager(this));
        mArticleList.setAdapter(adapter);
        //滑动到倒数int位置时调用LoadMore
        adapter.setPreLoadNumber(4);
        adapter.setEnableLoadMore(true);
        adapter.setLoadMoreView(new CustomLoadMoreView());
        adapter.setOnLoadMoreListener(() -> {
            //Presenter加载更多数据操作
            mainPresenter.getMoreArticle();

        });

    }

    @Override
    public void loadMoreArticle(List<ArticleBean.DataBean.DatasBean> datasBeanList) {
        if (adapter.getData().size() >= 50) {
            //数据全部加载完毕
            adapter.loadMoreEnd();
        } else {

            adapter.addData(datasBeanList);
            adapter.loadMoreComplete();

        }
    }

    @Override
    public void loadMoreArticleFail() {
        adapter.loadMoreFail();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

}
