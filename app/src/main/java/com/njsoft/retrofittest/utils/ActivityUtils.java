package com.njsoft.retrofittest.utils;

import android.content.Context;
import android.content.Intent;


/**
 * @author cui Activity工具类
 */
public class ActivityUtils {
    /**
     * @param context 上下文
     * @param c       目标对象
     */
    public static void goToActivity(Context context, Class c) {
        Intent intent = new Intent(context, c);
        context.startActivity(intent);
    }

    /**
     * @param context
     * @param c
     */
    public static void goToActivityWithFlag(Context context, Class c, String flag, String data) {
        Intent intent = new Intent(context, c);
        intent.putExtra(flag, data);
        context.startActivity(intent);
    }


}
