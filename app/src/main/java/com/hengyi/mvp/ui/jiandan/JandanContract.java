package com.hengyi.mvp.ui.jiandan;

import com.hengyi.mvp.bean.FreshNewsBean;
import com.hengyi.mvp.bean.JdDetailBean;
import com.hengyi.mvp.ui.base.BaseContract;

/**
 * Created by honghengqiang on 2018/2/20.
 */

public interface JandanContract {

    interface View extends BaseContract.BaseView{

        void loadFreshNews(FreshNewsBean freshNewsBean);
        void loadMoreFreshNews(FreshNewsBean freshNewsBean);

        void loadDetailData(String type, JdDetailBean jdDetailBean);
        void loadMoreDetailData(String type, JdDetailBean jdDetailBean);

    }

    interface Presenter extends BaseContract.BasePresenter<View>{

        void getData(String type , int page);
        void getFreshNews(int page);
        void getDetailData(String type, int page);

    }

}
