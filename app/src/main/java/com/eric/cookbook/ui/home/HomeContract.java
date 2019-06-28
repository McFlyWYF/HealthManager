package com.eric.cookbook.ui.home;

import android.content.Context;

import com.eric.cookbook.bean.MenuListBean;
import com.eric.cookbook.network.OkHttpCallback;

import java.util.List;
import java.util.Map;



public class HomeContract {
    interface IHomeView{
        void showBottom(int lastIndex);
        Context getCurContext();
        void showProgress();
        void hideProgress();
        void showData(List<MenuListBean.ResultBean.DataBean> dataBeans, int totalCount, int ItemTotalNum);
        void showInfo(String info);
    }

    interface IHomePresenter{
        void loadMenuList();

        void loadMoreMenu();
    }

    interface IHomeModel{
        void getMenuTotalList(Map<String, String> params , OkHttpCallback<MenuListBean.ResultBean> callback);
    }

}
