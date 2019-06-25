package com.eric.cookbook.bean.preference;

import com.eric.cookbook.bean.SearchHistoryBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/11.
 */

public abstract class BaseHistoryPref {
    /**
     * 保存历史记录时调用
     *
     * @param value
     */
    public abstract void save(String value);

    /**
     * 移除单条历史记录
     *
     * @param key
     */
    public abstract void remove(String key);

    /**
     * 清空历史记录
     */
    public abstract void clear();

    /**
     * 生成key
     *
     * @return
     */
    public abstract String generateKey();

    /**
     * 返回排序好的历史记录
     *
     * @return
     */
    public abstract ArrayList<SearchHistoryBean> sortHistory();

}
