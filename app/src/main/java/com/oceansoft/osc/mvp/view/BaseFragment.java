package com.oceansoft.osc.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oceansoft.osc.R;
import com.oceansoft.osc.moudle.base.view.navigation.impl.DefaultNavigation;

/**
 * Created by TempCw on 2017/1/29.
 */

public class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //设置导航栏（大多数Fragment导航栏用这个）但四个主Fragment不用这个
    public void bindNavigation(View view,int text){
        DefaultNavigation.Builder builder=new
                DefaultNavigation.Builder(getActivity(), (ViewGroup) view);
        builder.setCenterText(text)
                .setLeftImg(R.drawable.btn_node_pre_xml)
                .setRightImgOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();
                    }
                })
                .create();

    }
    //事项中心和个人中心（不带返回箭头的Fragement专用）
    public void bindDefaultNavigation(View view,int text){
        DefaultNavigation.Builder builder=new
                DefaultNavigation.Builder(getActivity(), (ViewGroup) view);
        builder.setCenterText(text)
                .create();
    }
}
