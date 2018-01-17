package com.hengyi.mvp.ui.jiandan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.hengyi.mvp.R;
import com.hengyi.mvp.component.ApplicationComponent;
import com.hengyi.mvp.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class JanDanFragment extends BaseFragment {

    @Override
    public int getContentLayout() {
        return R.layout.fragment_jan_dan;
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

    public static JanDanFragment newInstance() {
        Bundle args = new Bundle();
        JanDanFragment fragment = new JanDanFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
