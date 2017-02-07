package com.oceansoft.osc.mvp.view.lce.impl;

import android.view.View;

import com.oceansoft.osc.R;
import com.oceansoft.osc.mvp.view.lce.IMvpLceView;
import com.oceansoft.osc.mvp.view.lce.impl.animator.DefaultLceAnimator;
import com.oceansoft.osc.mvp.view.lce.impl.animator.ILceAnimator;

/**
 * Created by Jackie on 2017/1/29.
 * 目标接口实现类
 *
 */

public class MvpLceViewImpl<M> implements IMvpLceView<M> {
    private View loadingView;
    private View contentView;
    private View errorView;

    private ILceAnimator lceAnimator;

    /**
     * 初始化视图
     * @param v
     */
    public void initLceView(View v){
        if (loadingView == null) {
            loadingView = v.findViewById(R.id.loadingView);
        }
        if (contentView == null) {
            contentView = v.findViewById(R.id.contentView);
        }
        if (errorView == null) {
            errorView = v.findViewById(R.id.errorView);
        }
        if (loadingView == null) {
            throw new NullPointerException("loadingView is not null!");
        }
        if (contentView == null) {
            throw new NullPointerException("contentView is not null!");
        }
        if (errorView == null) {
            throw new NullPointerException("errorView is not null!");
        }
    }

    /**
     * 添加重新加载监听
     * @param onClickListener
     */
    public void setOnErrorViewClickListener(View.OnClickListener onClickListener){
        if (this.errorView!=null){
            this.errorView.setOnClickListener(onClickListener);
        }
    }

    private ILceAnimator getLceAnimator(){
        if (lceAnimator==null){
            return DefaultLceAnimator.getInstance();
        }
        return lceAnimator;
    }

    /**
     * 绑定动画执行策略
     * @param iLceAnimator
     */
    public void setLceAnimator(ILceAnimator iLceAnimator){
        this.lceAnimator=lceAnimator;
    }



    @Override
    public void showLoading(boolean pullToRefresh) {
        //如果不是下拉刷新，就显示这种视图(记得加判断，因为下拉刷新组件有正在加载的头部视图)
        if (!pullToRefresh) {
            getLceAnimator().showLoading(loadingView, contentView, errorView);
        }
    }

    @Override
    public void showContent() {
        getLceAnimator().showContent(loadingView,contentView,errorView);
    }

    @Override
    public void showError() {
        getLceAnimator().showErrorView(loadingView,contentView,errorView);
    }

    @Override
    public void bindData(M data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }
}
