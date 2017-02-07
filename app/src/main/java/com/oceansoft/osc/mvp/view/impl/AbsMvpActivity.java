package com.oceansoft.osc.mvp.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.oceansoft.osc.mvp.delegate.MvpDelegateCallback;
import com.oceansoft.osc.mvp.delegate.MvpDelegateCallbackProxy;
import com.oceansoft.osc.mvp.delegate.activity.ActivityMvpDelegateCallback;
import com.oceansoft.osc.mvp.presenter.IMvpPresenter;
import com.oceansoft.osc.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osc.mvp.view.BaseActivity;
import com.oceansoft.osc.mvp.view.IMvpView;

/**
 * Created by TempCw on 2017/1/29.
 */

public abstract class AbsMvpActivity<P extends AbsMvpPresenter,V extends IMvpView>
        extends BaseActivity implements ActivityMvpDelegateCallback<P,V>,IMvpView{
    private P presenter;

    private MvpDelegateCallbackProxy<P,V> proxy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用代理模式进行绑定
        proxy=new MvpDelegateCallbackProxy<>(this);
        proxy.bindPresenter();
        proxy.attchView();
        //普通模式绑定
//        if (presenter==null){
//            presenter=bindPresenter();
//        }
//        if (this.presenter!=null){
//            this.presenter.attachView(getMvpView());   //java语法，接口多态
//        }
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        proxy.detachView();
    }
}
