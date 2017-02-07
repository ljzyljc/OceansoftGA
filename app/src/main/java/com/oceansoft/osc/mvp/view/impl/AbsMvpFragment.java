package com.oceansoft.osc.mvp.view.impl;

import android.content.Context;

import com.oceansoft.osc.mvp.delegate.MvpDelegateCallbackProxy;
import com.oceansoft.osc.mvp.delegate.fragment.FragmentMvpDelegateCallback;
import com.oceansoft.osc.mvp.presenter.IMvpPresenter;
import com.oceansoft.osc.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osc.mvp.view.BaseFragment;
import com.oceansoft.osc.mvp.view.IMvpView;

/**
 * Created by TempCw on 2017/1/29.
 */

public abstract class AbsMvpFragment<P extends AbsMvpPresenter,V extends IMvpView>
        extends BaseFragment implements FragmentMvpDelegateCallback<P,V>,IMvpView{
    private P presenter;
    private MvpDelegateCallbackProxy<P,V> proxy;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        proxy=new MvpDelegateCallbackProxy<>(this);
        proxy.bindPresenter();
        proxy.attchView();

    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;   //其实这里就实现了绑定View
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        proxy.detachView();
    }
}
