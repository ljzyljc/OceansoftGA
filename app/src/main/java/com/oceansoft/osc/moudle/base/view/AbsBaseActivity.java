package com.oceansoft.osc.moudle.base.view;

import com.oceansoft.osc.moudle.base.presenter.AbsBasePresenter;
import com.oceansoft.osc.mvp.view.IMvpView;
import com.oceansoft.osc.mvp.view.impl.AbsMvpActivity;

/**
 * Created by TempCw on 2017/2/3.
 */

public abstract class AbsBaseActivity<P extends AbsBasePresenter,V extends IMvpView> extends AbsMvpActivity<P,V>{
    @Override
    public P bindPresenter() {
        return null;
    }

}
