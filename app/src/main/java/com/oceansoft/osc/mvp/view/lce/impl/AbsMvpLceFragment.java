package com.oceansoft.osc.mvp.view.lce.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.oceansoft.osc.moudle.base.view.AbsBaseFragment;
import com.oceansoft.osc.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osc.mvp.view.IMvpView;
import com.oceansoft.osc.mvp.view.lce.IMvpLceView;

/**
 * Created by TempCw on 2017/2/7.
 */

public abstract class AbsMvpLceFragment<M,P extends AbsMvpPresenter,V extends IMvpView>
            extends AbsBaseFragment<P,V> implements IMvpLceView<M> {
    private MvpLceViewImpl<M> lceViewImpl;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (lceViewImpl==null){
            lceViewImpl=new MvpLceViewImpl<M>();
        }
        initLceView(view);

    }
    private void initLceView(View view) {
        lceViewImpl.initLceView(view);
        lceViewImpl.setOnErrorViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorClick();
            }
        });

    }
    public void onErrorClick(){
        loadData(false);
    }

    @Override
    public void showLoading(boolean isPullToRefresh) {
    lceViewImpl.showLoading(isPullToRefresh);
    }

    @Override
    public void showContent() {
        lceViewImpl.showContent();
    }

    @Override
    public void showError() {
        lceViewImpl.showError();
    }

    @Override
    public void bindData(M data) {
        lceViewImpl.bindData(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        lceViewImpl.loadData(pullToRefresh);
    }
}
