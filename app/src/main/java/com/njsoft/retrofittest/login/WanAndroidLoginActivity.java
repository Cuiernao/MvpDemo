package com.njsoft.retrofittest.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.njsoft.retrofittest.MainActivity;
import com.njsoft.retrofittest.mvp.contract.LoginContract;
import com.njsoft.retrofittest.mvp.presenter.LoginPresenter;
import com.njsoft.retrofittest.utils.ActivityUtils;
import com.njsoft.retrofittest.R;
import com.njsoft.retrofittest.basetop.BaseTopActivity;
import com.njsoft.retrofittest.utils.DialogProgressUtill;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 玩Android登录页面
 */
public class WanAndroidLoginActivity extends BaseTopActivity implements LoginContract.LoginView {

    @BindView(R.id.UserName)
    EditText mUserName;
    @BindView(R.id.Password)
    EditText mPassword;
    @BindView(R.id.Login)
    Button mLogin;
    @BindView(R.id.Cancel)
    Button mCancel;
    private LoginContract.LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_wan_android_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("WanAndroid登录", R.color.white);
        setmToolBarRighText(getString(R.string.Register), R.color.white);
        setToolBarColor(R.color.colorPrimary);
        line.setVisibility(View.GONE);
    }

    @Override
    public void mToolbarRightClick(View view) {
        super.mToolbarRightClick(view);
        ActivityUtils.goToActivity(this, WanAndroidRegisterActivity.class);
    }

    @OnClick({R.id.Login, R.id.Cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Login:
                DialogProgressUtill.INSTANCE.initCancel(WanAndroidLoginActivity.this, "加载中");
                presenter.loadDate();
                break;
            case R.id.Cancel:
                break;
            default:
        }
    }


    @Override
    public void login() {
        presenter.loadDate();
    }

    @Override
    public void showPro(String result) {
        DialogProgressUtill.INSTANCE.dissDialog();
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        if (result.contains("成功")) {
            ActivityUtils.goToActivity(this, MainActivity.class);
        }
    }

    @Override
    public String getUserName() {
        return mUserName.getText().toString().trim();
    }

    @Override
    public String getPawword() {
        return mPassword.getText().toString().trim();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
