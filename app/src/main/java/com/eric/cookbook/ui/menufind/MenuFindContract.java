package com.eric.cookbook.ui.menufind;

/**
 * Created by Administrator on 2017/4/11.
 */

public class MenuFindContract {
    interface IMenuFindView{
        void replaceFragment(String content);
    }

    interface IMenuFindModel{
        void save(String content);
    }

    interface IMenuFindPresenter{
        void saveContent(String content);
    }
}
