package com.eric.cookbook.bean.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.eric.cookbook.bean.SearchHistoryBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;



public class HistoryPref extends BaseHistoryPref {
    private Context context;
    public static final String SEARCH_HISTORY = "search_history";
    private static SimpleDateFormat mFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private int HISTORY_MAX = 5;
    private static HistoryPref instance;

    private HistoryPref(Context context, int historyMax) {
        this.context = context.getApplicationContext();
        this.HISTORY_MAX = historyMax;
    }

    public static synchronized HistoryPref getInstance(Context context, int historyMax) {
        if (instance == null) {
            synchronized (HistoryPref.class) {
                if (instance == null) {
                    instance = new HistoryPref(context, historyMax);
                }
            }
        }
        return instance;
    }

    @Override
    public void save(String value) {
        Map<String, String> historys = (Map<String, String>) getAll();
        for (Map.Entry<String, String> entry : historys.entrySet()) {
            if (value.equals(entry.getValue())) {
                remove(entry.getKey());
            }
        }
        put(generateKey(), value);
    }

    @Override
    public void remove(String key) {
        SharedPreferences sp = context.getSharedPreferences(SEARCH_HISTORY,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    @Override
    public void clear() {
        SharedPreferences sp = context.getSharedPreferences(SEARCH_HISTORY,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public String generateKey() {
        return mFormat.format(new Date());
    }

    @Override
    public ArrayList<SearchHistoryBean> sortHistory() {
        Map<String, ?> allHistory = getAll();
        ArrayList<SearchHistoryBean> mResults = new ArrayList<>();
        Map<String, String> hisAll = (Map<String, String>) getAll();
        //将key排序升序
        Object[] keys = hisAll.keySet().toArray();
        Arrays.sort(keys);
        int keyLeng = keys.length;
        //这里计算 如果历史记录条数是大于 可以显示的最大条数，则用最大条数做循环条件，防止历史记录条数-最大条数为负值，数组越界
        int hisLeng = keyLeng > HISTORY_MAX ? HISTORY_MAX : keyLeng;
        for (int i = 1; i <= hisLeng; i++) {
            mResults.add(new SearchHistoryBean((String) keys[keyLeng - i], hisAll.get(keys[keyLeng - i])));
        }
        return mResults;
    }

    public boolean contains(String key) {
        SharedPreferences sp = context.getSharedPreferences(SEARCH_HISTORY,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    public Map<String, ?> getAll() {
        SharedPreferences sp = context.getSharedPreferences(SEARCH_HISTORY,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }

    public void put(String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(SEARCH_HISTORY,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
