package com.hengyi.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.hengyi.mvp.component.ApplicationComponent;
import com.hengyi.mvp.ui.base.BaseActivity;
import com.hengyi.mvp.ui.base.SupportFragment;
import com.hengyi.mvp.ui.jiandan.JanDanFragment;
import com.hengyi.mvp.ui.news.NewsFragment;
import com.hengyi.mvp.ui.personal.PersonalFragment;
import com.hengyi.mvp.ui.video.VideoFragment;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.contentContainer)
    FrameLayout contentContainer;
    @BindView(R.id.bottomBar)
    BottomNavigationView bottomBar;

    private SupportFragment[] mFragments = new SupportFragment[4];

    private int prePosition = 0;

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0, null);

        if (savedInstanceState == null) {
            mFragments[0] = NewsFragment.newInstance();
            mFragments[1] = VideoFragment.newInstance();
            mFragments[2] = JanDanFragment.newInstance();
            mFragments[3] = PersonalFragment.newInstance();

            getSupportDelegate().loadMultipleRootFragment(R.id.contentContainer, 0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2],
                    mFragments[3]);

        } else {
            mFragments[0] = findFragment(NewsFragment.class);
            mFragments[1] = findFragment(VideoFragment.class);
            mFragments[2] = findFragment(JanDanFragment.class);
            mFragments[3] = findFragment(PersonalFragment.class);
        }


        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_news:
                        getSupportDelegate().showHideFragment(mFragments[0], mFragments[prePosition]);
                        prePosition = 0;
                        break;
                    case R.id.tab_video:
                        getSupportDelegate().showHideFragment(mFragments[1], mFragments[prePosition]);
                        prePosition = 1;
                        break;
                    case R.id.tab_jiandan:
                        getSupportDelegate().showHideFragment(mFragments[2], mFragments[prePosition]);
                        prePosition = 2;
                        break;
                    case R.id.tab_my:
                        getSupportDelegate().showHideFragment(mFragments[3], mFragments[prePosition]);
                        prePosition = 3;
                        break;
                    default:
                        break;
                }
                return true;
            }
        });


    }

    @Override
    public void initData() {

    }

    @Override
    public void onRetry() {

    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

}
