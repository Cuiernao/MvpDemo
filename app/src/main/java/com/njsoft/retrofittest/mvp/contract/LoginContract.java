package com.njsoft.retrofittest.mvp.contract;

/**
 * 登录契约类
 */
public interface LoginContract {
    /**
     * 登录数据
     */
    interface LoginModel {
       void login(String userName,String password);
    }

    /**
     * 登录视图接口
     */
    interface LoginView {
       void login();
       void showPro(String result);
       String getUserName();
       String getPawword();
    }

    /**
     * 登录控制器
     */
    interface LoginPresenter {

        void loadDate();
        /**
         * 开始执行
         */
        void onStart();

        /**
         * 处理销毁问题防止内存泄漏
         */
        void onDestroy();
    }

}
