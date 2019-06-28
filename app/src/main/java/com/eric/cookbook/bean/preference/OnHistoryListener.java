package com.eric.cookbook.bean.preference;

import com.eric.cookbook.bean.SearchHistoryBean;

import java.util.ArrayList;


public interface OnHistoryListener {
    void onSortSuccess(ArrayList<SearchHistoryBean> results);
}
