package com.oceansoft.osc.moudle.base.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oceansoft.osc.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osc.mvp.view.IMvpView;
import com.oceansoft.osc.mvp.view.impl.AbsMvpFragment;

/**
 * Created by Jackie on 2017/2/3.
 * 可用于展示或者加载动画的Activity,对应的布局中要添加固定ID，方便统一控制动画
 */

public abstract class AbsBaseFragment<P extends AbsMvpPresenter,V extends IMvpView> extends AbsMvpFragment<P,V>{
    private View contentView;

    private boolean isInit;
    @Override
    public P bindPresenter() {
        return null;
    }

    @Override
    public P getPresenter() {
        return super.getPresenter();
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (contentView==null){
            contentView=inflater.inflate(bindLayoutId(),container,false);
            initContentView(contentView);
        }
        ViewGroup parent= (ViewGroup) contentView.getParent();
        if (parent!=null){
            parent.removeView(contentView);
        }
        return contentView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isInit){
            this.isInit=true;
            initData();
        }
    }
    protected void resetContentView(View contentView){
        ViewGroup viewGroup= (ViewGroup) contentView;
        viewGroup.removeAllViews();

    }
    protected void loadLayout(int layoutId,View v){
        ViewGroup viewGroup= (ViewGroup) v;
        View view=LayoutInflater.from(getContext()).inflate(layoutId,viewGroup,false);
        ViewGroup parent= (ViewGroup) view.getParent();
        if (parent!=null){
            parent.removeView(view);
        }
        viewGroup.addView(view);
    }
    public abstract int bindLayoutId();

    public void initData(){

    }
    public abstract void initContentView(View contentView);
}
