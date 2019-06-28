package com.eric.cookbook.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.eric.cookbook.R;
import com.eric.cookbook.bean.CookCategory;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;


class CategoryTagAdapter extends TagAdapter<CookCategory.ResultBean.ListBean> {
    private LayoutInflater mInflater;

    public CategoryTagAdapter(Context context, List<CookCategory.ResultBean.ListBean> datas) {
        super(datas);
        mInflater = LayoutInflater.from(context);
    }


//    public CategoryTagAdapter(Context context, List<CookCategory.ResultBean.ListBean> datas) {
//        super(datas);
//        mInflater = LayoutInflater.from(context);
//    }

    @Override
    public View getView(FlowLayout parent, int position, CookCategory.ResultBean.ListBean md) {
        TextView tv = (TextView) mInflater.inflate(R.layout.category_sort_tag,
                parent, false);

        tv.setBackgroundResource(R.drawable.shape_category_check);
        tv.setTextColor(Color.parseColor("#40a5f3"));
//        }else{
//            tv.setBackgroundResource(R.drawable.shape_wiki_drug_normal);
//            tv.setTextColor(Color.parseColor("#333333"));
//        }
        tv.setText(md.getName());
        return tv;
    }
}
