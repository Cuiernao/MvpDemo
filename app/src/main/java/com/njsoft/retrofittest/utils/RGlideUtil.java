package com.njsoft.retrofittest.utils;



import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.njsoft.retrofittest.GlideApp;
import com.njsoft.retrofittest.R;
import com.njsoft.retrofittest.view.GlideCircleTransform;

import java.lang.ref.WeakReference;


//import com.bumptech.glide.request.RequestOptions;

/**
 *@author  Created by arthur on 2017/3/13.
 * glide 工具
 */
public class RGlideUtil {
    /**
     * 加载图片
     *
     * @param context   上下文
     * @param url       路径
     * @param imageView view
     */
    public static void setImage(Context context, String url, ImageView imageView) {
        final WeakReference<ImageView> imageViewWeakReference = new WeakReference<>(imageView);
        ImageView target = imageViewWeakReference.get();
        if (target != null) {
            GlideApp.with(context).load(url)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    //缓存策略
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(target);
        }
    }

    /**
     * 带Cache的图片显示策略
     * @param context 上下文
     * @param url  路径
     * @param imageView 控件
     */
    public static void setImageWithCache(Context context, String url, ImageView imageView) {

        final WeakReference<ImageView> imageViewWeakReference = new WeakReference<>(imageView);
        ImageView target = imageViewWeakReference.get();
        if (target != null) {
            GlideApp.with(context)
                    .load(url)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .fallback(R.mipmap.ic_launcher)
                    .fitCenter()
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(target);
        }

    }

    /**
     * 裁剪圆形图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void setRoundImageWithCache(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .fallback(R.mipmap.ic_launcher)
                .fitCenter()
                //圆形
                .transform(new GlideCircleTransform(context))
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }

    /**
     * 拍照后显示圆形图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void setRoundImagePhone(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fallback(R.mipmap.ic_launcher)
                .fitCenter()
                //圆形
                .transform(new GlideCircleTransform(context))
//                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }
    public static void setBitmap(Context context, Bitmap bitmap, ImageView imageView) {
        GlideApp.with(context)
                .load(bitmap)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fallback(R.mipmap.ic_launcher)
                .fitCenter()
                //圆形
                .transform(new GlideCircleTransform(context))
//                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }
    /**
     * 清楚内存缓存,必须在UI线程调用
     */
    public static void clearMemory(Context context) {
        GlideApp.get(context).clearMemory();
    }
}
