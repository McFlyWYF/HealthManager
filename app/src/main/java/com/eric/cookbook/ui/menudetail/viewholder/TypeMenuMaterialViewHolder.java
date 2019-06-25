package com.eric.cookbook.ui.menudetail.viewholder;

import android.view.View;
import android.widget.TextView;

import com.eric.cookbook.R;
import com.eric.cookbook.bean.MenuMaterialBean;


/**
 * Created by Administrator on 2017/4/6.
 */

public class TypeMenuMaterialViewHolder extends TypeAbsViewHolder<MenuMaterialBean> {
    TextView material_name_tv;
    TextView material_num_tv;
    public TypeMenuMaterialViewHolder(View itemView) {
        super(itemView);
        material_name_tv = (TextView) itemView.findViewById(R.id.material_name_tv);
        material_num_tv = (TextView) itemView.findViewById(R.id.material_num_tv);
    }

    @Override
    public void bindViewHolder(MenuMaterialBean menuMaterialBean) {
        material_name_tv.setText(menuMaterialBean.getMaterialName());
        material_num_tv.setText(menuMaterialBean.getMaterialNum());
    }
}
