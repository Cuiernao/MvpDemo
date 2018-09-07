package com.njsoft.retrofittest.utils;

import android.content.Context;
import android.graphics.Color;

import com.zyao89.view.zloading.ZLoadingDialog;

import static com.zyao89.view.zloading.Z_TYPE.ROTATE_CIRCLE;

/**
 * @author Administrator 枚举类型（）
 */
public enum DialogProgressUtill {
    /**
     * 加载动画实例
     */
    INSTANCE;
    ZLoadingDialog dialog;
    public void init(Context context, String text) {
        dialog = new ZLoadingDialog(context);
        //设置类型
        dialog.setLoadingBuilder(ROTATE_CIRCLE)
                //颜色
                .setLoadingColor(Color.WHITE)
                .setHintText(text)
                .setCanceledOnTouchOutside(false)
//                .setHintTextSize(16) // 设置字体大小 dp
                // 设置字体颜色
                .setHintTextColor(Color.GRAY)
//                .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
                // 设置背景色，默认白色
                .setDialogBackgroundColor(Color.parseColor("#CC111111"))
                .show();
    }
    public void initCancel(Context context, String text) {
        dialog = new ZLoadingDialog(context);
        dialog.setLoadingBuilder(ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.WHITE)//颜色
                .setHintText(text)
                .setCanceledOnTouchOutside(true)
//                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.GRAY)  // 设置字体颜色
//                .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#CC111111")) // 设置背景色，默认白色
                .show();
    }
    public void dissDialog() {
        if (dialog!=null) {
            dialog.dismiss();
        }
    }
}