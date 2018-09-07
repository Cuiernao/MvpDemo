package com.njsoft.retrofittest.mvp;

import com.njsoft.retrofittest.api.wanAndroidbean;

/**
 * author: Cuiernao
 * date:   On 2018/9/6
 */
public class BaseModel<T> {
    private ModelListener<T> listener;

    public ModelListener<T> getListener() {
        return listener;
    }

    public void setListener(ModelListener<T> listener) {
        this.listener = listener;
    }
}
