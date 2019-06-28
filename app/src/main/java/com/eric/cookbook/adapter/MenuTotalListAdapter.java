package com.eric.cookbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.eric.cookbook.R;
import com.eric.cookbook.bean.MenuListBean;

import java.util.ArrayList;
import java.util.List;


public class MenuTotalListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MenuListBean.ResultBean.DataBean> dataBeanList;
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_FOOTER = 1;
    protected int mLastPosition = -1;
    protected boolean mIsShowFooter;
    protected OnItemClickListener mOnItemClickListener;
    private LayoutInflater layoutInflater;

    public MenuTotalListAdapter(Context mContext){
        layoutInflater = LayoutInflater.from(mContext);
        dataBeanList = new ArrayList<>();
    }


    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_FOOTER:
                view = layoutInflater.inflate(R.layout.item_load_more, parent, false);
                return new FooterViewHolder(view);
            case TYPE_ITEM:
                view = layoutInflater.inflate(R.layout.item_menu_list, parent, false);
                return new ItemViewHolder(view);
            default:
                throw new RuntimeException("there is no type that matches the type " +
                        viewType + " + make sure your using types correctly");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            setItemValues((ItemViewHolder) holder, position);
            if (position > mLastPosition/* && !isFooterPosition(position)*/) {
                mLastPosition = position;
            }
        }
    }

    private void setItemValues(ItemViewHolder holder, int position) {
        MenuListBean.ResultBean.DataBean dataBean = dataBeanList.get(position);
        holder.tv_menu_title.setText(dataBean.getTitle());
        holder.tv_menu_imtro.setText(dataBean.getImtro());
        Glide.with(holder.itemView.getContext()).load(dataBean.getAlbums().get(0)).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.iv_menu_album);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (mIsShowFooter && isFooterPosition(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }
    @Override
    public int getItemCount() {
        if (dataBeanList == null) {
            return 0;
        }
        int itemSize = dataBeanList.size();
        if (mIsShowFooter) {
            itemSize += 1;
        }
        return itemSize;
    }

    protected boolean isFooterPosition(int position) {
        return (getItemCount() - 1) == position;
    }

    public List<MenuListBean.ResultBean.DataBean> getDataBeanList() {
        return dataBeanList;
    }

    public void setDataBeanList(List<MenuListBean.ResultBean.DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
    }
//    public void add(int position, T item) {
//        mList.add(position, item);
//        notifyItemInserted(position);
//    }
//
    public void addMore(List<MenuListBean.ResultBean.DataBean> data) {
        int startPosition = dataBeanList.size();
        dataBeanList.addAll(data);
        notifyItemRangeInserted(startPosition, dataBeanList.size());
    }
//
//    public void delete(int position) {
//        mList.remove(position);
//        notifyItemRemoved(position);
//    }

    public void showFooter() {
        mIsShowFooter = true;
        notifyItemInserted(getItemCount());
    }

    public void hideFooter() {
        mIsShowFooter = false;
        notifyItemRemoved(getItemCount());
    }

    protected class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_menu_imtro;
        TextView tv_menu_title;
        ImageView iv_menu_album;
        public ItemViewHolder(View view) {
            super(view);
            tv_menu_imtro = (TextView) view.findViewById(R.id.menu_imtro_tv);
            tv_menu_title = (TextView)view.findViewById(R.id.menu_title_tv);
            iv_menu_album = (ImageView)view.findViewById(R.id.menu_album_iv);
        }
    }
}
