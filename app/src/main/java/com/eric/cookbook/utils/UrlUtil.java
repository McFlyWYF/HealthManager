package com.eric.cookbook.utils;

import com.eric.cookbook.Constants;

/**
 * Created by Administrator on 2017/4/15.
 */

public class UrlUtil {

    public static String generateUrl(int ysjkType, int currentPage){
        currentPage = currentPage >  0 ? currentPage : 1;
        String urlStr = "";
        switch (ysjkType){
            case Constants.YSJK_TYPE_LATEST:
                urlStr += Constants.YSJK_LATEST_LIST_URL;
                break;
            case Constants.YSJK_TYPE_HOT:
                urlStr += Constants.YSJK_HOT_LIST_URL;
                break;
       }

        urlStr += "List_" + currentPage + ".html";
        return urlStr;
    }
}
