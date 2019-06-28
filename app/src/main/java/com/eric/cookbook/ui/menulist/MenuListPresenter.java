package com.eric.cookbook.ui.menulist;

import android.util.Log;

import com.eric.cookbook.Constants;
import com.eric.cookbook.bean.MenuListBean;
import com.eric.cookbook.network.OkHttpCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MenuListPresenter implements MenuListContract.IMenuListPresenter {

    MenuListContract.IMenuListModel iMenuListModel;
    MenuListContract.IMenuListView iMenuListView;

    private Map<String, String> paramMap;
    private List<MenuListBean.ResultBean.DataBean> dataBeans;
    private int startNum = 0;

    MenuListPresenter(MenuListContract.IMenuListView menuListView){
        this.iMenuListView = menuListView;
        iMenuListModel = new MenuListModel();
        this.paramMap = new HashMap<>();
        dataBeans = new ArrayList<>();
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    @Override
    public void loadMenuList(final boolean clean, final String type) {
        iMenuListModel.getMenuList(paramMap, new OkHttpCallback<MenuListBean.ResultBean>() {
            @Override
            public void onSuccess(MenuListBean.ResultBean resultBean) {
                Log.d("MenuList++++ResultBean",  resultBean + "");
                if(type.equals(Constants.TYPE_FIRST_LOAD)) {
                    iMenuListView.hideProgress();
                }else if(type.equals(Constants.TYPE_REFRESH)){
                    iMenuListView.hideRefresh();
                }
                if (clean) {
                    dataBeans.clear();
                    startNum = 0;
                }
                Log.d("MenuList++++DataBeans--",  resultBean.getData() + "");
                dataBeans.addAll(resultBean.getData());
                iMenuListView.showData(dataBeans, Integer.parseInt(resultBean.getTotalNum()));
                iMenuListView.showBottom(startNum - 30);
            }

            @Override
            public void onFail(String errorMsg) {
                iMenuListView.hideProgress();
                iMenuListView.showInfo(errorMsg);
            }
        });
        startNum = startNum + 30;
    }

    @Override
    public void loadMoreMenu() {
        if(startNum == 0){
            startNum = 30;
        }
        paramMap.put(Constants.REQUEST_DATA_POINT_NUM, String.valueOf(startNum));
        loadMenuList(false, Constants.TYPE_LOAD_MORE);
    }
}
