package com.oceansoft.osc.moudle.base.presenter;

import android.content.Context;

import com.oceansoft.osc.moudle.base.model.AbsBaseModel;
import com.oceansoft.osc.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osc.mvp.view.IMvpView;

/**
 * Created by TempCw on 2017/2/3.
 */

public abstract class AbsBasePresenter<M extends AbsBaseModel,V extends IMvpView>
        extends AbsMvpPresenter<M,V>{
    public AbsBasePresenter(Context context) {
        super(context);
    }
//    public interface OnUIThreadListener<T>{
//        public void onResult(T result,String errorMessage);
//    }

    public void Onunsubscribe() {   //RxJava+retrofit取消订阅

    }
}
