package com.eric.cookbook.ui.menudetail;

import com.eric.cookbook.bean.MenuListBean;
import com.eric.cookbook.network.OkHttpCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/5.
 */

public class MenuDetailPresenter implements MenuDetailContract.IMenuDetailPresenter{

    private Map<String,  String> paramMap;
    private MenuDetailContract.IMenuDetailView iMenuDetailView;
    private MenuDetailContract.IMenuDetailModel iMenuDetailModel;
    private List<MenuListBean.ResultBean.DataBean> dataBeen;
    MenuDetailPresenter(MenuDetailContract.IMenuDetailView menuDetailView){
        this.iMenuDetailView = menuDetailView;
        iMenuDetailModel = new MenuDetailModel();
        paramMap = new HashMap<>();
        dataBeen = new ArrayList<>();
    }

    public void setParamMap(Map<String, String> params) {
        this.paramMap = params;
    }

    @Override
    public void loadMenuDetail() {
        iMenuDetailModel.getMenuDetail(paramMap, new OkHttpCallback<MenuListBean.ResultBean.DataBean>(){

            @Override
            public void onSuccess(MenuListBean.ResultBean.DataBean dataBean) {
                iMenuDetailView.hideProgress();
                iMenuDetailView.setDetailView(dataBean);
            }

            @Override
            public void onFail(String errorMsg) {
                iMenuDetailView.hideProgress();
                iMenuDetailView.showInfo(errorMsg);
            }
        });
    }
}
