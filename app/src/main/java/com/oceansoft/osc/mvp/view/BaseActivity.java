package com.oceansoft.osc.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import com.oceansoft.osc.R;
import com.oceansoft.osc.moudle.base.view.navigation.impl.DefaultNavigation;

/**
 * Created by Jackie on 2017/1/29.
 */

public class BaseActivity extends FragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //设置导航栏（大多数导航栏用这个）
    public void bindNavigation(int id, int text, final Activity activity){
        DefaultNavigation.Builder builder=new
                DefaultNavigation.Builder(activity, (ViewGroup) findViewById(id));
        builder.setCenterText(text)
                .setLeftImg(R.drawable.btn_node_pre_xml)
                .setRightImgOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.finish();
                    }
                })
            .create();

    }


}
