package com.oceansoft.osc.config;

import android.app.Application;
import android.content.Context;

/**
 * Created by TempCw on 2017/2/3.
 */

public class BaseApplication extends Application{
    private static BaseApplication instance=null;
    private static Context context;

    public static BaseApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        instance=this;
//        initApplication();
    }
}
