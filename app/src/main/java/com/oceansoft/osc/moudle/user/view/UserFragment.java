package com.oceansoft.osc.moudle.user.view;

import android.view.View;

import com.oceansoft.osc.R;
import com.oceansoft.osc.moudle.base.view.AbsBaseFragment;
import com.oceansoft.osc.moudle.user.presenter.UserPresenter;
import com.oceansoft.osc.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osc.mvp.view.BaseFragment;

/**
 * Created by TempCw on 2017/2/3.
 */

public class UserFragment extends AbsBaseFragment<UserPresenter,IUserView> implements IUserView{
    @Override
    public int bindLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    public void initContentView(View contentView) {
        //设置导航栏
        bindDefaultNavigation(contentView,R.string.user);

    }

    @Override
    public UserPresenter bindPresenter() {
        return new UserPresenter(getActivity());
    }
}
