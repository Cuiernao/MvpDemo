package com.njsoft.retrofittest.mvp.model;

import com.njsoft.retrofittest.ApiManager;
import com.njsoft.retrofittest.api.wanAndroidbean;
import com.njsoft.retrofittest.mvp.BaseModel;
import com.njsoft.retrofittest.mvp.ModelListener;
import com.njsoft.retrofittest.mvp.contract.RegisterContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterModel extends BaseModel<wanAndroidbean> implements RegisterContract.Model {

    @Override
    public void goRegister(String userName, String pw, String pwa) {
        if(getListener()==null) {
            return;
        }
        Call<wanAndroidbean> call= ApiManager
                .getInstance()
                .getApiService()
                .wanAndroidRegister(userName, pw, pwa);

        call.enqueue(new Callback<wanAndroidbean>() {
            @Override
            public void onResponse(Call<wanAndroidbean> call, Response<wanAndroidbean> response) {

                if (response.isSuccessful()){
                    getListener().modelSuccessful(response.body());
                }else {
                    getListener().modelErro("注册异常");
                }
            }

            @Override
            public void onFailure(Call<wanAndroidbean> call, Throwable t) {
                getListener().modelErro("注册异常"+t.toString());
            }
        });
    }
}
