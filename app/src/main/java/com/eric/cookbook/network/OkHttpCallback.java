package com.eric.cookbook.network;

public interface OkHttpCallback<T> {

    void onSuccess(T t); //请求成功回调
    void onFail(String errorMsg);
}
