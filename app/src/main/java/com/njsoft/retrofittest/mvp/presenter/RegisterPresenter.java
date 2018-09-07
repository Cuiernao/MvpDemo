package com.njsoft.retrofittest.mvp.presenter;

import android.widget.Toast;

import com.njsoft.retrofittest.api.wanAndroidbean;
import com.njsoft.retrofittest.mvp.BaseModel;
import com.njsoft.retrofittest.mvp.ModelListener;
import com.njsoft.retrofittest.mvp.contract.RegisterContract;
import com.njsoft.retrofittest.mvp.model.RegisterModel;

public class RegisterPresenter implements RegisterContract.Presenter, ModelListener<wanAndroidbean> {
    private RegisterContract.View view;
    private RegisterModel model;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
        model = new RegisterModel();
    }

    @Override
    public void registerData() {
        if (view != null) {
            //注册监听器的位置
            view.showProgress();
            model.setListener(this);
            model.goRegister(view.getUserName(), view.getPassword(), view.getPasswordAgagin());

        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void modelSuccessful(wanAndroidbean androidbean) {
        view.hideProgress();
        view.registerSuccessful(androidbean);
    }

    @Override
    public void modelErro(String error) {
        view.hideProgress();
        view.registerError(error);
    }
}
