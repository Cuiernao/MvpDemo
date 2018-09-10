package com.njsoft.retrofittest.basetop;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.jaeger.library.StatusBarUtil;
import com.njsoft.retrofittest.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 基础
 */
public abstract class BaseTopActivity extends AppCompatActivity {//标题
    //标题
    TextView mToolBarTitle;
    //右键图片
    ImageView mToolBarRight;
    //返回按钮
    ImageView mToolBarBack;
    //右侧文字
    TextView mToolBarRighText;
    //Toolbar
    Toolbar mToolBar;
    //内容
    FrameLayout mContentFragment;
    public View line;

    /**
     * 初始化视图
     */
    private void initView() {
        mToolBarTitle = findViewById(R.id.mToolBar_title);
        mToolBarBack = findViewById(R.id.mToolBar_Back);
        mToolBarRight = findViewById(R.id.mToolBar_right);
        mToolBarRighText = findViewById(R.id.mToolBar_right_Text);
        mToolBar = findViewById(R.id.mToolBar);
        mContentFragment = findViewById(R.id.ContentFragment);
        line = findViewById(R.id.line);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_top);
        initView();
        LayoutInflater.from(this).inflate(getContentView(), mContentFragment);
        //初始化设置 Toolbar
        setSupportActionBar(mToolBar);
        init(savedInstanceState);
        ScreenUtils.isAdaptScreen();
    }

    public void setToolBarColor(int color) {
        mToolBar.setBackgroundColor(color(color));
        StatusBarUtil.setColor(this, color(color), 0);
    }

    @ColorInt
    public int color(@ColorRes int colorRes) {
        return ContextCompat.getColor(this, colorRes);
    }

    public abstract int getContentView();

    protected abstract void init(Bundle savedInstanceState);

    /**
     * 设置右侧文字，和颜色
     *
     * @param text  描述
     * @param color 颜色
     */
    public void setmToolBarRighText(String text, int color) {
        if (!TextUtils.isEmpty(text)) {
            mToolBarRighText.setText(text);
            if (color != 0) {
                mToolBarRighText.setTextColor(getResources().getColor(color));
            }
            mToolBarRighText.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置右侧图片
     *
     * @param id
     */
    public void setmToolBarRight(int id) {
        if (id != 0) {
            mToolBarRight.setImageResource(id);
            mToolBarRight.setVisibility(View.VISIBLE);

        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title, int color) {
        if (!TextUtils.isEmpty(title)) {
            if (color != 0) {
                mToolBarTitle.setTextColor(getResources().getColor(color));
            }
            mToolBarTitle.setText(title);
            mToolBarTitle.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置返回按钮
     */
    public void setLeftButton(int id) {
        if (id != 0) {
            mToolBarBack.setImageResource(id);
        }
        mToolBarBack.setVisibility(View.VISIBLE);
    }


    /**
     * 返回按钮点击事件
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }

    /**
     * 带图片的右键按钮
     *
     * @param view
     */
    public void mToolbarRightClick(View view) {

    }

}
