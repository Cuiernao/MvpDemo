package com.njsoft.retrofittest.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.njsoft.retrofittest.ApiManager;
import com.njsoft.retrofittest.R;
import com.njsoft.retrofittest.RetrofitUtils;
import com.njsoft.retrofittest.api.WanAndroidInterface;
import com.njsoft.retrofittest.api.wanAndroidbean;
import com.njsoft.retrofittest.basetop.BaseTopActivity;
import com.njsoft.retrofittest.mvp.contract.RegisterContract;
import com.njsoft.retrofittest.mvp.presenter.RegisterPresenter;
import com.njsoft.retrofittest.utils.DialogProgressUtill;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * 注册
 */
public class WanAndroidRegisterActivity extends BaseTopActivity implements RegisterContract.View {

    @BindView(R.id.UserName)
    EditText mUserName;
    @BindView(R.id.Password)
    EditText mPassword;
    @BindView(R.id.PasswordAgain)
    EditText mPasswordAgain;
    private String Tag = "-->";
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        presenter = new RegisterPresenter(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_wan_android_register;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle(getString(R.string.Register), 0);
        setmToolBarRighText(getString(R.string.Confrim), R.color.red);
    }

    @Override
    public void mToolbarRightClick(View view) {
        super.mToolbarRightClick(view);
        presenter.registerData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showProgress() {
        DialogProgressUtill.INSTANCE.init(this, "");
    }

    @Override
    public void hideProgress() {
        DialogProgressUtill.INSTANCE.dissDialog();
    }

    @Override
    public String getUserName() {
        return mUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPassword.getText().toString();
    }

    @Override
    public String getPasswordAgagin() {
        return mPasswordAgain.getText().toString();
    }

    @Override
    public void registerSuccessful(wanAndroidbean wanAndroid) {

        if (wanAndroid.getErrorCode() == 0) {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "" + wanAndroid.getErrorMsg(), Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void registerError(String error) {

        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }
}
