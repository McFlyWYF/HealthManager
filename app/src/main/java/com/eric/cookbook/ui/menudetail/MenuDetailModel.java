package com.eric.cookbook.ui.menudetail;

import com.eric.cookbook.bean.MenuListBean;
import com.eric.cookbook.network.Networks;
import com.eric.cookbook.network.OkHttpCallback;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Map;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/5.
 */

public class MenuDetailModel implements MenuDetailContract.IMenuDetailModel {
    @Override
    public void getMenuDetail(Map<String, String> params, final OkHttpCallback<MenuListBean.ResultBean.DataBean> callback) {
        Networks.getCookApi()
                .getMenuListById(Networks.getCacheControl(), params)
                .flatMap(new Func1<MenuListBean, Observable<MenuListBean.ResultBean.DataBean>>() {
                    @Override
                    public Observable<MenuListBean.ResultBean.DataBean> call(MenuListBean menuListBean) {
                        return Observable.from(menuListBean.getResult().getData());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MenuListBean.ResultBean.DataBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callback.onFail("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callback.onFail("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callback.onFail("网络连接超时!!");
                        } else {
                            callback.onFail("发生未知错误" + e.getMessage());

                        }
                    }

                    @Override
                    public void onNext(MenuListBean.ResultBean.DataBean dataBean) {
                        callback.onSuccess(dataBean);
                    }
                });
    }
}
