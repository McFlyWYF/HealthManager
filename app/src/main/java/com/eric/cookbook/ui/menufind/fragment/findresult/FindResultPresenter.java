package com.eric.cookbook.ui.menufind.fragment.findresult;

import android.util.Log;

import com.eric.cookbook.Constants;
import com.eric.cookbook.bean.MenuListBean;
import com.eric.cookbook.network.OkHttpCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/13.
 */

public class FindResultPresenter implements FindResultContract.IFindResultPresenter {
    private FindResultContract.IFindResultView findResultView;
    private FindResultContract.IFindResultModel findResultModel;

    private Map<String, String> params = new HashMap<>();
    private List<MenuListBean.ResultBean.DataBean> dataBeenList = new ArrayList<>();
    private int startNum = 0;
    public FindResultPresenter(FindResultContract.IFindResultView findResultView) {
        this.findResultView = findResultView;
        findResultModel = new FindResultModel();
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.params = paramMap;
    }

    @Override
    public void loadMenuResult() {
        findResultModel.getMenuResult(params, new OkHttpCallback<MenuListBean.ResultBean>() {
            @Override
            public void onSuccess(MenuListBean.ResultBean resultBean) {
                findResultView.hideProgress();
                Log.d("TAGSS",  resultBean.getData().size() + "daxiao wei");
                dataBeenList.addAll(resultBean.getData());
                findResultView.setResultView(dataBeenList, Integer.parseInt(resultBean.getTotalNum()));
                findResultView.showBottom(startNum - 30);
            }

            @Override
            public void onFail(String errorMsg) {
                findResultView.hideProgress();
                findResultView.showInfo(errorMsg);
            }
        });
        startNum = startNum + 30;
    }

    @Override
    public void loadMoreResult() {
        params.put(Constants.REQUEST_DATA_POINT_NUM, String.valueOf(startNum));
        loadMenuResult();
    }
}
