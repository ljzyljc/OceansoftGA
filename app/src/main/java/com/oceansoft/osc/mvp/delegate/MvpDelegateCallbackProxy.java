package com.oceansoft.osc.mvp.delegate;

import com.oceansoft.osc.mvp.presenter.IMvpPresenter;
import com.oceansoft.osc.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osc.mvp.view.IMvpView;

/**
 * Created by Jackie on 2017/1/29.
 * 使用代理模式进行绑定
 * 代理模式--静态代理--代理对象（需要持有目标接口实例）
 * ---标准的代理模式需要实现目标接口（这里不需要）,这里还是添加吧(方便自动实现方法) =。=
 * 其实很多框架的使用的设置模式并没有那么标准
 *
 */

public class MvpDelegateCallbackProxy<P extends AbsMvpPresenter,V extends IMvpView>
        implements MvpDelegateCallback<P,V>{

    private MvpDelegateCallback<P,V> mvpDelegateCallback;
    public MvpDelegateCallbackProxy(MvpDelegateCallback mvpDelegateCallback){
        this.mvpDelegateCallback=mvpDelegateCallback;
    }


    @Override
    public P bindPresenter() {
        P presenter=mvpDelegateCallback.getPresenter();
        if (presenter==null){
            presenter=mvpDelegateCallback.bindPresenter();
        }
        if (presenter==null){
            throw new NullPointerException("Presenter is not null!");
        }
        //绑定
        mvpDelegateCallback.setPresenter(presenter);

        return getPresenter();
    }

    @Override
    public P getPresenter() {
        P presenter=mvpDelegateCallback.getPresenter();
        if (presenter==null){
            //抛异常
            throw new NullPointerException("Presenter is not null");
        }
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        getPresenter().attachView(getMvpView());
    }

    @Override
    public V getMvpView() {
        return mvpDelegateCallback.getMvpView();
    }
    public void attchView(){
        getPresenter().attachView(getMvpView());
    }
    public void detachView(){
        getPresenter().detachView();
    }







}
