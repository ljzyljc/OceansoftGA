package com.oceansoft.osc.mvp.delegate;

import com.oceansoft.osc.mvp.presenter.IMvpPresenter;
import com.oceansoft.osc.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osc.mvp.view.IMvpView;

/**
 * Created by Jackie on 2017/1/29.
 *
 * 代理模式--静态代理：目标接口
 *
 */

public interface MvpDelegateCallback<P extends AbsMvpPresenter,V extends IMvpView>{

    /**
     * 创建Presenter
     * @return
     */
    public P bindPresenter();
    /**
     * 得到Presenter实例
     * @return
     */
    public P getPresenter();
    /**
     * 设置一个新的Presenter
     * @param presenter
     */
    public void setPresenter(P presenter);
    /**
     * 得到具体MVPView实例对象
     * @return
     */
    public V getMvpView();



}
