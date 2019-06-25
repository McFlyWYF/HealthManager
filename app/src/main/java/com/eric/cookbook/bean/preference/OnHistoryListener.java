package com.eric.cookbook.bean.preference;

import com.eric.cookbook.bean.SearchHistoryBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/11.
 */

public interface OnHistoryListener {
    void onSortSuccess(ArrayList<SearchHistoryBean> results);
}
