package com.oceansoft.osc.mvp.model.impl;

import android.content.Context;

import com.oceansoft.osc.mvp.model.IMvpModel;

/**
 * Created by Jackie on 2017/1/29.
 */

public abstract class AbsMvpModel implements IMvpModel{
    private Context context;

    public AbsMvpModel(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

}
