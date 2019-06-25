package com.eric.cookbook.ui.menudetail.viewholder;

import android.view.View;
import android.widget.TextView;

import com.eric.cookbook.R;
import com.eric.cookbook.bean.TitleBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/7.
 */

public class TypeTitleViewHolder extends TypeAbsViewHolder<TitleBean> {
    @Bind(R.id.rv_title_tv)
    TextView rv_title_tv;
    public TypeTitleViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,  itemView);
    }

    @Override
    public void bindViewHolder(TitleBean titleBean) {
        rv_title_tv.setText(titleBean.getTitle());
    }
}
