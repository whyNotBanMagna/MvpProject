package com.hengyi.mvp.module;

import android.util.Log;

import com.hengyi.mvp.MyApp;
import com.hengyi.mvp.net.ApiConstants;
import com.hengyi.mvp.net.JanDanApi;
import com.hengyi.mvp.net.JanDanApiService;
import com.hengyi.mvp.net.RetrofitConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by honghengqiang on 2018/2/21.
 */

@Module
public class HttpModule {

    @Provides
    OkHttpClient.Builder provideOkHttpClient() {
        // 指定缓存路径,缓存大小100Mb
        Log.d("TAG", "provideOkHttpClient: "+new File(MyApp.getContext().getCacheDir(), "HttpCache"));
        Cache cache = new Cache(new File(MyApp.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        return new OkHttpClient().newBuilder().cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(RetrofitConfig.sLoggingInterceptor)
                .addInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS);
    }


    @Provides
    JanDanApi provideJanDanApis(OkHttpClient.Builder builder) {

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return JanDanApi.getsInstance(retrofitBuilder
                .baseUrl(ApiConstants.sJanDanApi)
                .build().create(JanDanApiService.class));
    }
}
