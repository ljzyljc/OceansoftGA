package com.oceansoft.osc.moudle.home.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.oceansoft.osc.R;
import com.oceansoft.osc.moudle.base.view.AbsBaseFragment;
import com.oceansoft.osc.moudle.base.view.navigation.impl.DefaultNavigation;
import com.oceansoft.osc.moudle.home.presenter.HomePresenter;
import com.oceansoft.osc.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osc.mvp.view.BaseFragment;

/**
 * Created by TempCw on 2017/2/3.
 */

public class HomeFragment extends AbsBaseFragment<HomePresenter,IHomeView> implements IHomeView{
    @Override
    public int bindLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initContentView(View contentView) {
        initNavigation(contentView);
    }

    @Override
    public HomePresenter bindPresenter() {
        return new HomePresenter(getActivity());
    }


    /**
     * 设置导航栏(如果是一般的导航栏，用其他的),这里的导航栏比较特殊
     * @param contentView
     */
    public void initNavigation(View contentView){
        DefaultNavigation.Builder builder=new DefaultNavigation.Builder(getContext(), (ViewGroup) contentView);
        builder.setCenterText(R.string.osga)
                .setRightImg(R.drawable.icon_message)
                .setRightImgOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"点击了消息中心",Toast.LENGTH_SHORT).show();
                    }
                }).create();
    }



}
