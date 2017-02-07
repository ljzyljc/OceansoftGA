package com.oceansoft.osc.moudle.base.model;

import android.content.Context;

import com.oceansoft.osc.mvp.model.impl.AbsMvpModel;

/**
 * Created by TempCw on 2017/2/3.
 */

public abstract class AbsBaseModel extends AbsMvpModel{
    public AbsBaseModel(Context context) {
        super(context);
    }
    public void unsubscribe() {   //RxJava+retrofit取消订阅

    }
}
