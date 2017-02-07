package com.oceansoft.osc.mvp.presenter;

import com.oceansoft.osc.mvp.view.IMvpView;

/**
 * Created by TempCw on 2017/1/29.
 */

public interface IMvpPresenter<V extends IMvpView> {
    //绑定
    public void attachView(V view);
    //解除绑定
    public void detachView();


}
