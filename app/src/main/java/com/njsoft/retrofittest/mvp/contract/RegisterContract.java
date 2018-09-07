package com.njsoft.retrofittest.mvp.contract;

import com.njsoft.retrofittest.api.wanAndroidbean;

public interface RegisterContract {
    interface Model {
        /**
         * 请求注册
         * @param userName 用户名
         * @param pw 密码
         * @param pwa 再次输入密码
         */
        void goRegister(String userName,String pw,String pwa);
    }

    interface View {
        void showProgress();
        void hideProgress();
        /**
         * 获取用户名
         * @return 获取用户名
         */
        String getUserName();

        /**
         *
         * @return 返回密码
         */
        String getPassword();

        /**
         *返回密码
         * @return 返回密码
         */
        String getPasswordAgagin();

        /**
         * 成功回调
         * @param wanAndroid  成功获取到
         */
        void registerSuccessful(wanAndroidbean wanAndroid);

        /**
         * 失败
         * @param error 返回错误信息
         */
        void registerError(String error);
    }

    interface Presenter {
        /**
         * 行为注册
         */
        void registerData();
        void onDestroy();
    }
}
