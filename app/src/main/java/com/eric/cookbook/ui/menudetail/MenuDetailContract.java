package com.eric.cookbook.ui.menudetail;

import android.content.Context;

import com.eric.cookbook.bean.MenuListBean;
import com.eric.cookbook.network.OkHttpCallback;

import java.util.Map;


public class MenuDetailContract {
    /**
     * View 层接口封装
     */
    interface IMenuDetailView{
        Context getCurContext();
        void showProgress();
        void hideProgress();
        void setDetailView(MenuListBean.ResultBean.DataBean dataBeans);
        void showInfo(String info);
    }

    /**
     * Presenter用于连接View和Model
     */
    interface IMenuDetailPresenter{
        void loadMenuDetail();
    }

    /**
     * Model层进行业务逻辑的处理
     */
    interface IMenuDetailModel{
        void getMenuDetail(Map<String, String> params , OkHttpCallback<MenuListBean.ResultBean.DataBean> callback);
    }
}
