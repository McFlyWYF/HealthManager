package com.eric.cookbook.ui.home;

import android.util.Log;

import com.eric.cookbook.Constants;
import com.eric.cookbook.bean.MenuListBean;
import com.eric.cookbook.network.OkHttpCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/7.
 */

public class HomePresenter implements HomeContract.IHomePresenter {

    private HomeContract.IHomeModel homeModel;
    private HomeContract.IHomeView homeView;
    private Map<String, String> paramMap;
    private List<MenuListBean.ResultBean.DataBean> dataBeans;
    private int startNum = 0;
    private int cid = 1;
    private int totalNum;
    private int itemTotalNum;
    public HomePresenter(HomeContract.IHomeView homeView) {
        this.homeModel = new HomeModel();
        this.homeView = homeView;
        paramMap = new HashMap<>();
        dataBeans = new ArrayList<>();
    }

    @Override
    public void loadMenuList() {
        Log.d("TAG", paramMap + "");
        Log.d("TAG1", paramMap.get(Constants.MENU_CID) + "");
        Log.d("TAG2", paramMap.get(Constants.REQUEST_DATA_POINT_NUM) + "");
        Log.d("TAG3", paramMap.get(Constants.REQUEST_DATA_RANGW_NUM) + "");
        Log.d("TAG4", paramMap.get(Constants.RESULT_DATA_TYPE) + "");
        homeModel.getMenuTotalList(paramMap, new OkHttpCallback<MenuListBean.ResultBean>() {

            @Override
            public void onSuccess(MenuListBean.ResultBean resultBean) {
                Log.d("TAGS", resultBean.getData() + "");
                dataBeans.addAll(resultBean.getData());

                if(cid != Integer.parseInt(paramMap.get(Constants.MENU_CID))){
                    cid = Integer.parseInt(paramMap.get(Constants.MENU_CID));
                    totalNum = totalNum + itemTotalNum;
                      Log.d("GGGGGGGGG", "totalNum = " + totalNum + "----- " + "itemTotalNum = " + itemTotalNum);
                    itemTotalNum = Integer.parseInt(resultBean.getTotalNum());
                    Log.d("GGGGGGGGG", "----- " + "itemTotalNum = " + itemTotalNum);
                }else if(cid == 1){
                    totalNum = 0;
                    itemTotalNum = Integer.parseInt(resultBean.getTotalNum());
                }
                homeView.showData(dataBeans, totalNum, itemTotalNum);
//                homeView.showBottom(startNum - 30);
            }
            @Override
            public void onFail(String errorMsg) {
                homeView.hideProgress();
                homeView.showInfo(errorMsg);
            }
        });
        startNum = startNum + 30;
    }

    @Override
    public void loadMoreMenu() {
        if(cid != Integer.parseInt(paramMap.get(Constants.MENU_CID))){
            startNum = 0;
            paramMap.put(Constants.REQUEST_DATA_POINT_NUM, String.valueOf(startNum));
        }else {
            paramMap.put(Constants.REQUEST_DATA_POINT_NUM, String.valueOf(startNum));
        }
        loadMenuList();
    }

    public void setParamMap(Map<String, String> params) {
        this.paramMap = params;
    }
}
