package com.eric.cookbook.ui.menufind.fragment.findresult;

import android.content.Context;

import com.eric.cookbook.bean.MenuListBean;
import com.eric.cookbook.network.OkHttpCallback;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/13.
 */

public class FindResultContract {
    interface IFindResultView {
        Context getCurContext();
        void showProgress();
        void hideProgress();
        void setResultView(List<MenuListBean.ResultBean.DataBean> dataBeans, int totalCount);
        void showInfo(String info);
        void showBottom(int index);
    }
    interface IFindResultModel{
        void getMenuResult(Map<String,  String> params, OkHttpCallback<MenuListBean.ResultBean> callback);
    }
    interface IFindResultPresenter{
        void loadMenuResult();

        void loadMoreResult();
    }
}
