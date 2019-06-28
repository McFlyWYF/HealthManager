package com.eric.cookbook.ui.healthy.fragment;

import com.eric.cookbook.bean.HealthyEatingBean;
import com.eric.cookbook.network.OkHttpCallback;

import java.util.ArrayList;
import java.util.List;


public class HealthyEatingPresenter implements HealthyEatingContracts.IHealthyEatingPresenter {

    HealthyEatingContracts.IHealthyEatingModel healthyEatingModel;
    HealthyEatingContracts.IHealthyEatingView healthyEatingView;
    List<HealthyEatingBean> healthyEatingBeanList = new ArrayList<>();
    private int curPage;
    private int healthType = -1;
    public HealthyEatingPresenter(HealthyEatingContracts.IHealthyEatingView healthyEatingView, int healthType) {
        this.healthyEatingView = healthyEatingView;
        this.healthType = healthType;
        this.healthyEatingModel = new HealthyEatingModel();
        curPage = 1;
    }

    @Override
    public void loadHealthyData() {
        healthyEatingModel.loadHealthyEatingData(curPage, healthType, new OkHttpCallback<HealthyEatingBean>() {
            @Override
            public void onSuccess(HealthyEatingBean healthyEatingBean) {
                healthyEatingBeanList.add(healthyEatingBean);
                healthyEatingView.hideProgress();
                healthyEatingView.showData(healthyEatingBeanList, curPage);
                healthyEatingView.showBottom(healthyEatingBeanList.size() - 20);
            }

            @Override
            public void onFail(String errorMsg) {

            }
        });
    }

    @Override
    public void loadMoreHealthyData() {
        curPage++;
        loadHealthyData();
    }
}
