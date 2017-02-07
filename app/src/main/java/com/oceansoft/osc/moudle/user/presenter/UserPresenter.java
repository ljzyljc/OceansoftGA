package com.oceansoft.osc.moudle.user.presenter;

import android.content.Context;

import com.oceansoft.osc.moudle.user.model.UserModel;
import com.oceansoft.osc.moudle.user.view.IUserView;
import com.oceansoft.osc.mvp.presenter.impl.AbsMvpPresenter;

/**
 * Created by TempCw on 2017/2/7.
 */

public class UserPresenter extends AbsMvpPresenter<UserModel,IUserView> {
    public UserPresenter(Context context) {
        super(context);
    }

    @Override
    public UserModel bindModel() {
        return new UserModel(getContext());
    }
}
