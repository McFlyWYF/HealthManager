package com.eric.cookbook.ui.menufind;


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
