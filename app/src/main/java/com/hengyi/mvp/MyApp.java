package com.hengyi.mvp;


import com.hengyi.mvp.component.ApplicationComponent;
import com.hengyi.mvp.component.DaggerApplicationComponent;
import com.hengyi.mvp.module.ApplicationModule;
import com.hengyi.mvp.module.HttpModule;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * Created by Administrator on 2018/1/15.
 */

public class MyApp extends LitePalApplication {

    private static MyApp myApp;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        LitePal.initialize(this);
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .httpModule(new HttpModule())
                .build();
    }

    public static MyApp getInstance(){
        return myApp;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
