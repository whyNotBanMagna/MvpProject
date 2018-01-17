package com.hengyi.mvp.ui.news;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.hengyi.mvp.R;
import com.hengyi.mvp.component.ApplicationComponent;
import com.hengyi.mvp.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment {

    @Override
    public int getContentLayout() {
        return R.layout.fragment_news;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
