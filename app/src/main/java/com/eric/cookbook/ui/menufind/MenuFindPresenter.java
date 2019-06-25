package com.eric.cookbook.ui.menufind;

import android.content.Context;

/**
 * Created by Administrator on 2017/4/11.
 */

public class MenuFindPresenter implements MenuFindContract.IMenuFindPresenter {
    private MenuFindContract.IMenuFindModel menuFindModel;
    private MenuFindContract.IMenuFindView menuFindView;
    private static int HISTORY_MAX = 5;

    public MenuFindPresenter(MenuFindContract.IMenuFindView menuFindView, Context context) {
        this.menuFindModel = new MenuFindModel(context, HISTORY_MAX);
        this.menuFindView = menuFindView;
    }

    @Override
    public void saveContent(String content) {
        menuFindModel.save(content);
        menuFindView.replaceFragment(content);
    }
}
