package com.njsoft.retrofittest.mvp.model;

import com.njsoft.retrofittest.ApiManager;
import com.njsoft.retrofittest.api.wanAndroidbean;
import com.njsoft.retrofittest.mvp.ModelListener;
import com.njsoft.retrofittest.mvp.contract.LoginContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author cui
 */
public class LoginModel implements LoginContract.LoginModel {

    private ModelListener<wanAndroidbean> listener;
    public void setListener(ModelListener<wanAndroidbean> listener) {
        this.listener = listener;
    }
    @Override
    public void login(String userName, String password) {

        Call<wanAndroidbean> call = ApiManager.getInstance().getApiService().wanAndroidLogin(userName, password);
        call.enqueue(new Callback<wanAndroidbean>() {
            @Override
            public void onResponse(Call<wanAndroidbean> call, Response<wanAndroidbean> response) {
                if (response.isSuccessful()) {
                    listener.modelSuccessful(response.body());
                }else {
                    listener.modelErro("请求异常");
                }
            }
            @Override
            public void onFailure(Call<wanAndroidbean> call, Throwable t) {
                listener.modelErro("请求异常"+t.toString());
            }
        });

    }
}
