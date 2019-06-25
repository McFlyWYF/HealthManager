package com.eric.cookbook.ui.menulist;

import android.content.Context;

import com.eric.cookbook.bean.MenuListBean;
import com.eric.cookbook.network.OkHttpCallback;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/1
 * MVP接口分装类，使结构更加清晰.
 */

public class MenuListContract {

    /**
     * View 层接口封装
     */
    interface IMenuListView{
        void showBottom(int lastIndex);
        Context getCurContext();
        void showProgress();
        void hideProgress();
        void showData(List<MenuListBean.ResultBean.DataBean> dataBeans,  int totalCount);
       void showInfo(String info);
        void showRefresh();
        void hideRefresh();
    }

    /**
     * Presenter用于连接View和Model
     */
    interface IMenuListPresenter{
        void loadMenuList(boolean clean, String type);

        void loadMoreMenu();
    }

    /**
     * Model层进行业务逻辑的处理
      */
    interface IMenuListModel{
        void getMenuList(Map<String, String> params , OkHttpCallback<MenuListBean.ResultBean> callback);
    }
}
