package com.njsoft.retrofittest.mvp.presenter;

import android.text.TextUtils;

//import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.njsoft.retrofittest.api.wanAndroidbean;
import com.njsoft.retrofittest.login.WanAndroidLoginActivity;
import com.njsoft.retrofittest.mvp.ModelListener;
import com.njsoft.retrofittest.mvp.contract.LoginContract;
import com.njsoft.retrofittest.mvp.model.LoginModel;

public class LoginPresenter implements LoginContract.LoginPresenter, ModelListener<wanAndroidbean> {
    private LoginModel model;
    private LoginContract.LoginView loginView;

    public LoginPresenter(WanAndroidLoginActivity loginView) {
        this.loginView = loginView;
        model = new LoginModel();
    }

    @Override
    public void loadDate() {
        if (loginView != null) {
            if (!NetworkUtils.isConnected()) {
                loginView.showPro("未检测到网络！");
                return;
            }
            if (!TextUtils.isEmpty(loginView.getUserName()) && !TextUtils.isEmpty(loginView.getPawword())) {
                model.setListener(this);
                model.login(loginView.getUserName(), loginView.getPawword());

            } else {
                loginView.showPro("用户名，密码不能为空");
            }
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void modelSuccessful(wanAndroidbean androidbean) {
        if (androidbean.getErrorCode() != 0) {
            loginView.showPro(androidbean.getErrorMsg());
        } else {
            loginView.showPro("登录成功！");
        }
    }

    @Override
    public void modelErro(String error) {
        loginView.showPro(error);
    }
}
