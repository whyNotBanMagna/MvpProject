package com.hengyi.mvp.ui.personal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.hengyi.mvp.R;
import com.hengyi.mvp.component.ApplicationComponent;
import com.hengyi.mvp.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends BaseFragment {


    @Override
    public int getContentLayout() {
        return R.layout.fragment_personal;
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

    public static PersonalFragment newInstance() {
        Bundle args = new Bundle();
        PersonalFragment fragment = new PersonalFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
