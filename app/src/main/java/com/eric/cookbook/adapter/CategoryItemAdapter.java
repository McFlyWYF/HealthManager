package com.eric.cookbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.eric.cookbook.Constants;
import com.eric.cookbook.R;
import com.eric.cookbook.bean.CookCategory;
import com.eric.cookbook.ui.menulist.MenuListActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;


public class CategoryItemAdapter extends CommonAdapter<CookCategory.ResultBean> {

    public CategoryItemAdapter(Context context, List<CookCategory.ResultBean> datas) {
        super(context, R.layout.fragment_sort_right_item, datas);
    }

    @Override
    protected void convert(final ViewHolder holder, final CookCategory.ResultBean listBeen, int position) {
        holder.setText(R.id.item_main_right_title, listBeen.getName());
        TagFlowLayout flowlayout = holder.getView(R.id.item_main_right_taglayout);
        final List<CookCategory.ResultBean.ListBean> categoryItemBeen = listBeen.getList();
        final CategoryTagAdapter tagAdapter=new CategoryTagAdapter(mContext, categoryItemBeen);

        flowlayout.setAdapter(tagAdapter);
        flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                //跳转至MenuListActivity并将
                Intent intent = new Intent(holder.getConvertView().getContext(), MenuListActivity.class);
                intent.putExtra(Constants.MENU_CID,categoryItemBeen.get(position).getId());
                (holder.getConvertView().getContext()).startActivity(intent);
                tagAdapter.notifyDataChanged();
                return false;
            }
        });
    }
}
