package com.hengyi.mvp.component;

import android.content.Context;

import com.hengyi.mvp.MyApp;
import com.hengyi.mvp.module.ApplicationModule;

import dagger.Component;

/**
 * Created by Administrator on 2018/1/15.
 */

@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    MyApp getApplication();
    Context getContext();
}
