package com.hengyi.mvp.ui.video;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.hengyi.mvp.R;
import com.hengyi.mvp.component.ApplicationComponent;
import com.hengyi.mvp.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment {

    @Override
    public int getContentLayout() {
        return R.layout.fragment_video;
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

    public static VideoFragment newInstance() {
        Bundle args = new Bundle();
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
