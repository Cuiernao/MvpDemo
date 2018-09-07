package com.njsoft.retrofittest.api;


import com.njsoft.retrofittest.bean.ArticleBean;
import com.njsoft.retrofittest.bean.BannerBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * WanAndroid API
 */
public interface WanAndroidInterface {
    /**
     * 用户注册接口
     *
     * @param userName      用户名
     * @param passWord      密码
     * @param passWordAgain 再次输入密码
     * @return 返回值
     */
    @FormUrlEncoded
    @POST("user/register")
    Call<wanAndroidbean> wanAndroidRegister(@Field("username") String userName, @Field("password") String passWord, @Field("repassword") String passWordAgain);

    @FormUrlEncoded
    @POST("user/register")
    Observable<wanAndroidbean> rXWanAndroidRegister(@Field("username") String userName, @Field("password") String passWord, @Field("repassword") String passWordAgain);


    /**
     * 玩安卓登录
     *
     * @param name     用户名
     * @param passWord 密码
     * @return 返回Call对象
     */
    @FormUrlEncoded
    @POST("user/login")
    Call<wanAndroidbean> wanAndroidLogin(@Field("username") String name, @Field("password") String passWord);

    /**
     * 获取Baner列表
     * @return 返回轮播图
     */
    @GET("banner/json")
    Call<BannerBean> getWanAndroidBraner();

    /**
     * http://www.wanandroid.com/article/list/{page}/json
     * @return 返回首页文章
     */
    @GET("article/list/{page}/json")
    Call<ArticleBean> getWanAndroidArticel(@Path("page") int page);
}
