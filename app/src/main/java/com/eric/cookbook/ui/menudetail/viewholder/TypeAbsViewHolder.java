package com.eric.cookbook.ui.menudetail.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/4/6.
 */

public abstract class TypeAbsViewHolder<T> extends RecyclerView.ViewHolder {
    public TypeAbsViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindViewHolder(T t);
}
