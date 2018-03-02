package com.hengyi.mvp.component;

import android.content.Context;

import com.hengyi.mvp.MyApp;
import com.hengyi.mvp.module.ApplicationModule;
import com.hengyi.mvp.module.HttpModule;
import com.hengyi.mvp.net.JanDanApi;

import dagger.Component;

/**
 * Created by Administrator on 2018/1/15.
 */

@Component(modules = {ApplicationModule.class,HttpModule.class})
public interface ApplicationComponent {

    MyApp getApplication();

    JanDanApi getJanDanApi();

    Context getContext();
}
