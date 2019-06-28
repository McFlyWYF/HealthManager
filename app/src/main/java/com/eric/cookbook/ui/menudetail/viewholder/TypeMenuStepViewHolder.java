package com.eric.cookbook.ui.menudetail.viewholder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eric.cookbook.R;
import com.eric.cookbook.bean.MenuListBean;

public class TypeMenuStepViewHolder extends TypeAbsViewHolder<MenuListBean.ResultBean.DataBean.StepsBean> {
    TextView step_tv;
    ImageView step_iv;
    public TypeMenuStepViewHolder(View itemView) {
        super(itemView);
        step_tv = (TextView) itemView.findViewById(R.id.step_tv);
        step_iv = (ImageView) itemView.findViewById(R.id.step_iv);
    }

    @Override
    public void bindViewHolder(MenuListBean.ResultBean.DataBean.StepsBean stepsBean) {
        step_tv.setText(stepsBean.getStep());
        Glide.with(itemView.getContext()).load(stepsBean.getImg()).into(step_iv);
    }
}
