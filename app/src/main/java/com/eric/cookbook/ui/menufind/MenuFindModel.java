package com.eric.cookbook.ui.menufind;

import android.content.Context;

import com.eric.cookbook.bean.preference.BaseHistoryPref;
import com.eric.cookbook.bean.preference.HistoryPref;

/**
 * Created by Administrator on 2017/4/11.
 */

public class MenuFindModel implements MenuFindContract.IMenuFindModel {

    private BaseHistoryPref historyStorage;

    public MenuFindModel(Context context, int historyMax) {
        this.historyStorage = HistoryPref.getInstance(context, historyMax);
    }
    @Override
    public void save(String content) {
        historyStorage.save(content);
    }
}
