package com.eric.cookbook.ui.healthdetail;

import com.eric.cookbook.network.OkHttpCallback;



public class HealthDetailContract {

    interface IHealthDetailView{
        void showProgress();
        void hideProgress();
        void showData(String result);
        void showMessage(String msg);
    }

    interface IHealthDetailPresenter{
        void loadHealthData();
    }

    interface IHealthDetailModel{
        void loadDetailData(String health_id, OkHttpCallback<String> callback);
    }
}
