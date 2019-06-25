package com.eric.cookbook.network;


/**
 * Created by Administrator on 2017/3/31.
 */

public interface OkHttpCallback<T> {

    void onSuccess(T t); //请求成功回调
    void onFail(String errorMsg);
}
