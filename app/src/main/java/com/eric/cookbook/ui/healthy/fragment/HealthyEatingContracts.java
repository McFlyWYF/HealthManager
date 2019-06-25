package com.eric.cookbook.ui.healthy.fragment;

import com.eric.cookbook.bean.HealthyEatingBean;
import com.eric.cookbook.network.OkHttpCallback;

import java.util.List;

/**
 * Created by Administrator on 2017/4/15.
 */

public class HealthyEatingContracts {
    interface IHealthyEatingView{
        void showProgress();
        void hideProgress();
        void showData(List<HealthyEatingBean> healthyEatingList, int curPage);
        void showBottom(int lastIndex);
        void showInfo(String info);
    }

    interface IHealthyEatingPresenter{
        void loadHealthyData();
        void loadMoreHealthyData();
    }

    interface IHealthyEatingModel{
        void loadHealthyEatingData(int curPage, int ysjkType, OkHttpCallback<HealthyEatingBean> callback);
    }
}
