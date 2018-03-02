package com.hengyi.mvp.ui.jiandan;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hengyi.mvp.R;
import com.hengyi.mvp.component.ApplicationComponent;
import com.hengyi.mvp.net.JanDanApi;
import com.hengyi.mvp.ui.adapter.FreshNewsAdapter;
import com.hengyi.mvp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class JanDanFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    private JanDanPagerAdapter mJanDanPagerAdapter;


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
        List<String> strings = new ArrayList<>();
        strings.add("新鲜事");
        strings.add("无聊图");
        strings.add("妹子图");
        strings.add("段子");
        mJanDanPagerAdapter = new JanDanPagerAdapter(getChildFragmentManager(),strings);
        mViewpager.setAdapter(mJanDanPagerAdapter);
        mViewpager.setOffscreenPageLimit(1);
        mViewpager.setCurrentItem(0,false);
        mTablayout.setupWithViewPager(mViewpager,true);

    }

    public static JanDanFragment newInstance() {
        Bundle args = new Bundle();
        JanDanFragment fragment = new JanDanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public class JanDanPagerAdapter extends FragmentStatePagerAdapter{

        private List<String> titles;

        public JanDanPagerAdapter(FragmentManager fm,List<String> titles) {
            super(fm);
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0 :
                    return JdDetailFragment.newInstance(JanDanApi.TYPE_FRESH,new FreshNewsAdapter(null));
                case 1 :
                    return JdDetailFragment.newInstance(JanDanApi.TYPE_FRESH,new FreshNewsAdapter(null));
                case 2 :
                    return JdDetailFragment.newInstance(JanDanApi.TYPE_FRESH,new FreshNewsAdapter(null));
                case 3 :
                    return JdDetailFragment.newInstance(JanDanApi.TYPE_FRESH,new FreshNewsAdapter(null));
                default:
                    break;
            }
            return null;
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}
