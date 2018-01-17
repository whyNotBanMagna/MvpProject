package com.hengyi.mvp.ui.inter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hengyi.mvp.component.ApplicationComponent;


public interface IBase {

    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    View getView();

    int getContentLayout();

    void initInjector(ApplicationComponent appComponent);

    void bindView(View view, Bundle savedInstanceState);

    void initData();

}
