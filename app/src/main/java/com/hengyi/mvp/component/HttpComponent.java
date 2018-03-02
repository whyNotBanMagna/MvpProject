package com.hengyi.mvp.component;

import com.hengyi.mvp.ui.jiandan.JdDetailFragment;

import dagger.Component;

/**
 * Created by honghengqiang on 2018/2/21.
 */
@Component(dependencies = ApplicationComponent.class)
public interface HttpComponent {

    void inject(JdDetailFragment jdDetailFragment);

}
