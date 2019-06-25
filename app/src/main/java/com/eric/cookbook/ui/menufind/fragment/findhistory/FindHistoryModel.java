package com.eric.cookbook.ui.menufind.fragment.findhistory;

import android.content.Context;

import com.eric.cookbook.bean.preference.BaseHistoryPref;
import com.eric.cookbook.bean.preference.HistoryPref;
import com.eric.cookbook.bean.preference.OnHistoryListener;

/**
 * Created by Administrator on 2017/4/12.
 */

public class FindHistoryModel implements FindHistoryContract.IFindHistoryModel {

    private BaseHistoryPref historyPref;

    public FindHistoryModel(Context context, int historyMax) {
        this.historyPref = HistoryPref.getInstance(context, historyMax);
    }


    @Override
    public void remove(String key, OnHistoryListener onHistoryListener) {
        historyPref.remove(key);
        onHistoryListener.onSortSuccess(historyPref.sortHistory());
    }

    @Override
    public void clear(OnHistoryListener onHistoryListener) {
        historyPref.clear();
        onHistoryListener.onSortSuccess(historyPref.sortHistory());
    }

    @Override
    public void sortHistory(OnHistoryListener onHistoryListener) {
        onHistoryListener.onSortSuccess(historyPref.sortHistory());
    }
}
