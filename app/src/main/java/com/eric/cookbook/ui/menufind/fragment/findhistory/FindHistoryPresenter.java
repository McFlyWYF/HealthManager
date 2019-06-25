package com.eric.cookbook.ui.menufind.fragment.findhistory;

import android.content.Context;

import com.eric.cookbook.bean.SearchHistoryBean;
import com.eric.cookbook.bean.preference.OnHistoryListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/12.
 */

public class FindHistoryPresenter implements FindHistoryContract.IFindHistoryPresenter {

    private FindHistoryContract.IFindHistoryModel findHistoryModel;
    private FindHistoryContract.IFindHistoryView findHistoryView;
    private static int HISTORY_MAX = 5;

    public FindHistoryPresenter(FindHistoryContract.IFindHistoryView findHistoryView, Context context) {
        findHistoryModel = new FindHistoryModel(context, HISTORY_MAX);
        this.findHistoryView = findHistoryView;
    }

    @Override
    public void removeHistory(String key) {
        findHistoryModel.remove(key, new OnHistoryListener() {
            @Override
            public void onSortSuccess(ArrayList<SearchHistoryBean> results) {
                findHistoryView.showHistories(results);
            }
        });
    }

    @Override
    public void clearHistory() {
        findHistoryModel.clear(new OnHistoryListener() {
            @Override
            public void onSortSuccess(ArrayList<SearchHistoryBean> results) {
                findHistoryView.showHistories(results);
            }
        });
    }

    @Override
    public void sortHistory() {
        findHistoryModel.sortHistory(new OnHistoryListener() {
            @Override
            public void onSortSuccess(ArrayList<SearchHistoryBean> results) {
                findHistoryView.showHistories(results);
            }
        });
    }
}
