package com.hengyi.mvp.ui.jiandan;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hengyi.mvp.R;
import com.hengyi.mvp.bean.FreshNewsBean;
import com.hengyi.mvp.bean.JdDetailBean;
import com.hengyi.mvp.component.ApplicationComponent;
import com.hengyi.mvp.component.DaggerHttpComponent;
import com.hengyi.mvp.ui.base.BaseFragment;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class JdDetailFragment extends BaseFragment<JanDanPresenter> implements JandanContract.View{

    public static final String TYPE = "type";
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mPtrFrameLayout)
    PtrClassicFrameLayout mPtrFrameLayout;

    private BaseQuickAdapter mAdapter;
    private int pageNum = 1;
    private String type;


    public JdDetailFragment(BaseQuickAdapter adapter) {
        this.mAdapter = adapter;
    }


    public static JdDetailFragment newInstance(String type, BaseQuickAdapter baseQuickAdapter) {
        Bundle args = new Bundle();
        args.putCharSequence(TYPE, type);
        JdDetailFragment fragment = new JdDetailFragment(baseQuickAdapter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_jd_detail;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        mPtrFrameLayout.disableWhenHorizontalMove(true);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mRecyclerView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNum = 1;
                mPresenter.getData(type, pageNum);

            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setEnableLoadMore(true);
        mAdapter.setPreLoadNumber(1);
        //mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.getData(type, pageNum);
            }
        }, mRecyclerView);
    }

    @Override
    public void initData() {
        if (getArguments() == null) {
            return;
        }
        type = getArguments().getString(TYPE);
        mPresenter.getData(type, pageNum);
    }

    @Override
    public void onRetry() {
        initData();
    }

    @Override
    public void loadFreshNews(FreshNewsBean freshNewsBean) {
        if(freshNewsBean==null) {
            mPtrFrameLayout.refreshComplete();
            showFaild();
        }else {
            pageNum++;
            mAdapter.setNewData(freshNewsBean.getPosts());
            mPtrFrameLayout.refreshComplete();
            showSuccess();
        }
    }

    @Override
    public void loadMoreFreshNews(FreshNewsBean freshNewsBean) {
        if (freshNewsBean == null) {
            mAdapter.loadMoreFail();
        } else {
            pageNum++;
            mAdapter.addData(freshNewsBean.getPosts());
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void loadDetailData(String type, JdDetailBean jdDetailBean) {

    }

    @Override
    public void loadMoreDetailData(String type, JdDetailBean jdDetailBean) {

    }
}
