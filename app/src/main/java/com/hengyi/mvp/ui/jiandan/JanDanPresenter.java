package com.hengyi.mvp.ui.jiandan;

import com.hengyi.mvp.bean.FreshNewsBean;
import com.hengyi.mvp.net.BaseObserver;
import com.hengyi.mvp.net.JanDanApi;
import com.hengyi.mvp.net.RxSchedulers;
import com.hengyi.mvp.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by honghengqiang on 2018/2/21.
 */

public class JanDanPresenter extends BasePresenter<JandanContract.View> implements JandanContract.Presenter{

    private static final String TAG = "JanDanPresenter";
    JanDanApi mJanDanApi;

    @Inject
    public JanDanPresenter(JanDanApi janDanApi){
        this.mJanDanApi = janDanApi;
    }

    @Override
    public void getData(String type, int page) {
        if(type.equals(JanDanApi.TYPE_FRESH)) {
            getFreshNews(page);
        }else {
            getDetailData(type,page);
        }
    }

    @Override
    public void getFreshNews(final int page) {
        mJanDanApi.getFreshNews(page)
                .compose(RxSchedulers.<FreshNewsBean>applySchedulers())
                .compose(mView.<FreshNewsBean>bindToLife())
                .subscribe(new BaseObserver<FreshNewsBean>() {
                    @Override
                    public void onSucess(FreshNewsBean freshNewsBean) {
                        if(page>1) {
                            mView.loadMoreFreshNews(freshNewsBean);
                        }else {
                            mView.loadFreshNews(freshNewsBean);
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }

    @Override
    public void getDetailData(String type, int page) {

    }
}
