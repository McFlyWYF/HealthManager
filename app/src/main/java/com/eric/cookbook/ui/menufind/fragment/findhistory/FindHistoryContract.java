package com.eric.cookbook.ui.menufind.fragment.findhistory;

import com.eric.cookbook.bean.SearchHistoryBean;
import com.eric.cookbook.bean.preference.OnHistoryListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/11.
 */

public class FindHistoryContract {
    interface IFindHistoryView{
        void showHistories(ArrayList<SearchHistoryBean> results);
    }

    interface IFindHistoryModel{

        void remove(String key, OnHistoryListener onHistoryListener);

        void clear(OnHistoryListener onHistoryListener);

        void sortHistory(OnHistoryListener onHistoryListener);
    }

    interface IFindHistoryPresenter{
        void removeHistory(String key);

        void clearHistory();

        void sortHistory();
    }

}
