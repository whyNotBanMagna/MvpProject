package com.hengyi.mvp.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hengyi.mvp.MyApp;
import com.hengyi.mvp.ui.inter.IBase;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/16.
 * author
 */

public abstract class BaseFragment<T1 extends BaseContract.BasePresenter> extends SupportFragment implements IBase, BaseContract.BaseView {

    protected Context mContext;
    protected View mRootView;
    protected Dialog mLoadingDialog = null;
    Unbinder unbinder;

    @Nullable
    @Inject
    protected T1 mPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView != null) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        } else {
            mRootView = createView(inflater, container, savedInstanceState);
        }

        mContext = mRootView.getContext();
        //mLoadingDialog = DialogHelper.getLoadingDialog(getActivity());
        return mRootView;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initInjector(MyApp.getInstance().getApplicationComponent());
        attachView();
        bindView(view, savedInstanceState);
        initStateView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initData();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    @Nullable
    @Override
    public View getView() {
        return mRootView;
    }

    private void initStateView() {

    }

    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showFaild() {

    }

    @Override
    public void showNoNet() {

    }

    @Override
    public void onRetry() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
