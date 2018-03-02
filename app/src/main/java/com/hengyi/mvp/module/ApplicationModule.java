package com.hengyi.mvp.module;

import android.content.Context;

import com.hengyi.mvp.MyApp;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private Context mContext;

    public ApplicationModule(Context context) {
        this.mContext = context;
    }

    @Provides
    MyApp provideApplication() {
        return (MyApp) mContext.getApplicationContext();
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}
