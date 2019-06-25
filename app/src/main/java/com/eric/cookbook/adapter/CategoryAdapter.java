package com.eric.cookbook.adapter;

import android.content.Context;
import android.view.View;

import com.eric.cookbook.R;
import com.eric.cookbook.bean.CookCategory;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */

public class CategoryAdapter extends CommonAdapter<CookCategory.ResultBean> {

    private int currentItem;

    public CategoryAdapter(Context context, List<CookCategory.ResultBean> datas) {
        super(context, R.layout.fragment_sort_left_item, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, CookCategory.ResultBean item, int position) {
        if(position == currentItem){
            viewHolder.getView(R.id.item_main_left_bg).setVisibility(View.VISIBLE);
        }else{
            viewHolder.getView(R.id.item_main_left_bg).setVisibility(View.GONE);
        }
        viewHolder.setText(R.id.item_main_left_type,  item.getName());
    }

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }

    public int getCurrentItem() {
        return currentItem;
    }
}
