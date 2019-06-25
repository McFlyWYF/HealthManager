package com.eric.cookbook.ui.healthdetail;

import com.eric.cookbook.network.OkHttpCallback;

/**
 * Created by Administrator on 2017/5/5.
 */

public class HealthDetailPresenter implements HealthDetailContract.IHealthDetailPresenter{

    private HealthDetailContract.IHealthDetailModel healthDetailModel;
    private HealthDetailContract.IHealthDetailView healthDetailView;
    private String healthId;
    public HealthDetailPresenter(HealthDetailContract.IHealthDetailView healthDetailView) {
        this.healthDetailView = healthDetailView;
        healthDetailModel =  new HealthDetailModel();
    }

    public void setHealthId(String id){
        this.healthId = id;
    }

    @Override
    public void loadHealthData() {
        healthDetailModel.loadDetailData(healthId, new OkHttpCallback<String>() {
            @Override
            public void onSuccess(String s) {
                healthDetailView.hideProgress();
                healthDetailView.showData(s);
            }

            @Override
            public void onFail(String errorMsg) {

            }
        });
    }
}
