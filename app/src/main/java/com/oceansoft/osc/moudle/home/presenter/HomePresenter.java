package com.oceansoft.osc.moudle.home.presenter;

import android.content.Context;

import com.oceansoft.osc.moudle.base.presenter.AbsBasePresenter;
import com.oceansoft.osc.moudle.home.model.HomeModel;
import com.oceansoft.osc.moudle.home.view.IHomeView;

/**
 * Created by TempCw on 2017/2/7.
 */

public class HomePresenter extends AbsBasePresenter<HomeModel,IHomeView> implements IHomeView {
    public HomePresenter(Context context) {
        super(context);
    }

    @Override
    public HomeModel bindModel() {
        return new HomeModel(getContext());
    }
}
