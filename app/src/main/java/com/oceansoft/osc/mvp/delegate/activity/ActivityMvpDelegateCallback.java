package com.oceansoft.osc.mvp.delegate.activity;

import com.oceansoft.osc.mvp.delegate.MvpDelegateCallback;
import com.oceansoft.osc.mvp.presenter.IMvpPresenter;
import com.oceansoft.osc.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osc.mvp.view.IMvpView;

/**
 * Created by TempCw on 2017/1/29.
 * 扩展目标接口 针对不同的模块，目标接口有差异
 *
 */

public interface ActivityMvpDelegateCallback<P extends AbsMvpPresenter,V extends IMvpView>
        extends MvpDelegateCallback<P,V>{
}
