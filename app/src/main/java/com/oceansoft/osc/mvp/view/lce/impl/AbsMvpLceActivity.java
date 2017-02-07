package com.oceansoft.osc.mvp.view.lce.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.oceansoft.osc.mvp.presenter.IMvpPresenter;
import com.oceansoft.osc.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osc.mvp.view.IMvpView;
import com.oceansoft.osc.mvp.view.impl.AbsMvpActivity;
import com.oceansoft.osc.mvp.view.lce.IMvpLceView;
import com.oceansoft.osc.mvp.view.lce.impl.animator.ILceAnimator;

/**
 * Created by Jackie on 2017/2/7.
 * 可用于展示或者加载动画的Activity,对应的布局中要添加固定ID，方便统一控制动画
 */

public abstract class AbsMvpLceActivity<M,P extends AbsMvpPresenter,V extends IMvpView> extends AbsMvpActivity<P,V>
        implements IMvpLceView<M>{
    private MvpLceViewImpl<M> lceViewImpl;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        initLceView(getWindow().getDecorView());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lceViewImpl=new MvpLceViewImpl<M>();
    }
    /**
     * 提供给子类配置自己想要的动画策略
     * @param iLceAnimator
     */
    public void setLceAnimator(ILceAnimator iLceAnimator){
        lceViewImpl.setLceAnimator(iLceAnimator);
    }
    private void initLceView(View v){
        lceViewImpl.initLceView(v);
        lceViewImpl.setOnErrorViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorClick();
            }
        });

    }

    @Override
    public P bindPresenter() {
        return null;
    }

    @Override
    public void showLoading(boolean isPullToRefresh) {
        lceViewImpl.showLoading(isPullToRefresh);
    }

    @Override
    public void showContent() {
        lceViewImpl.showContent();
    }

    @Override
    public void showError() {
        lceViewImpl.showError();
    }

    @Override
    public void bindData(M data) {
        lceViewImpl.bindData(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        lceViewImpl.loadData(pullToRefresh);
    }
    public void onErrorClick(){
        loadData(false);
    }
}
