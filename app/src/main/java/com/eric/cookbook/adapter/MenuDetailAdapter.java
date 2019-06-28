package com.eric.cookbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.eric.cookbook.R;
import com.eric.cookbook.bean.MenuListBean;
import com.eric.cookbook.bean.MenuMaterialBean;
import com.eric.cookbook.bean.TitleBean;
import com.eric.cookbook.ui.menudetail.viewholder.TypeMenuMaterialViewHolder;
import com.eric.cookbook.ui.menudetail.viewholder.TypeMenuStepViewHolder;
import com.eric.cookbook.ui.menudetail.viewholder.TypeTitleViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MenuDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;

    protected static final int TYPE_STEP = 0;
    protected static final int TYPE_MATERIAL = 1;
    protected static final int TYPE_MATERIAL_HEADER = 2;
    protected static final int TYPE_STEP_HEADER = 3;

    public MenuDetailAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    //存放各个list的type
    private List<Integer> types = new ArrayList<>();

    //存放各个list的大小，键是type，值是大小
    private Map<Integer,Integer> mPositions = new HashMap<>();

    private List<MenuListBean.ResultBean.DataBean.StepsBean> listStepBean;
    private List<MenuMaterialBean> listMaterialBean;
    private List<TitleBean> listMaterialTitleBean = new ArrayList<>();
    private List<TitleBean> listStepTitleBean = new ArrayList<>();

    //使用此方法从mainactivity获取数据,这样就不用从构造方法里传数据了
    public void addList(List<MenuListBean.ResultBean.DataBean.StepsBean> stepsBeen, List<MenuMaterialBean> menuMaterialBeen){
        TitleBean materialTitleBean = new TitleBean();
        materialTitleBean.setTitle("食材");
        listMaterialTitleBean.add(materialTitleBean);
        TitleBean stepTitleBean = new TitleBean();
        stepTitleBean.setTitle("步骤");
        listStepTitleBean.add(stepTitleBean);
        addItemByType(TYPE_MATERIAL_HEADER,listMaterialTitleBean);
        addItemByType(TYPE_MATERIAL,menuMaterialBeen);
        addItemByType(TYPE_STEP_HEADER,listStepTitleBean);
        addItemByType(TYPE_STEP,stepsBeen);

        listStepBean = stepsBeen;
        listMaterialBean = menuMaterialBeen;
    }

    private void addItemByType(int type,List list){
        //注意传入的是types.size()
        mPositions.put(type,types.size());
        for (int i = 0; i < list.size(); i++) {
            types.add(type);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_MATERIAL_HEADER:
                return new TypeTitleViewHolder(mLayoutInflater.inflate(R.layout.item_rv_title,parent,false));
            case TYPE_MATERIAL:
                return new TypeMenuMaterialViewHolder(mLayoutInflater.inflate(R.layout.item_menu_material,parent,false));
            case TYPE_STEP_HEADER:
                return new TypeTitleViewHolder(mLayoutInflater.inflate(R.layout.item_rv_title,parent,false));
            case TYPE_STEP:
                return new TypeMenuStepViewHolder(mLayoutInflater.inflate(R.layout.item_menu_step,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        //获取每个view在列表里相对位置
        int realPositions = position - mPositions.get(viewType);
        switch (viewType){
            case TYPE_MATERIAL_HEADER:
                ((TypeTitleViewHolder)holder).bindViewHolder(listMaterialTitleBean.get(realPositions));
                break;
            case TYPE_MATERIAL:
                ((TypeMenuMaterialViewHolder)holder).bindViewHolder(listMaterialBean.get(realPositions));
                break;
            case TYPE_STEP_HEADER:
                ((TypeTitleViewHolder)holder).bindViewHolder(listStepTitleBean.get(realPositions));
                break;
            case TYPE_STEP:
                ((TypeMenuStepViewHolder)holder).bindViewHolder(listStepBean.get(realPositions));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return types.size();
    }
}
