package com.eric.cookbook.ui.menulist;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.eric.cookbook.Constants;
import com.eric.cookbook.R;
import com.eric.cookbook.bean.MenuListBean;
import com.eric.cookbook.ui.menudetail.MenuDetailActivity;
import com.eric.cookbook.utils.ToastUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;




public class MenuListActivity extends AppCompatActivity implements MenuListContract.IMenuListView, SwipeRefreshLayout.OnRefreshListener,CommonAdapter.OnItemClickListener {

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.rv_menus)
    RecyclerView rv_menus;
//    @Bind(R.id.tv_error)
//    TextView tv_error;

    private String cid;
    private Map<String, String> params = new HashMap<>();

    MenuListPresenter menuListPresenter;
    CommonAdapter<MenuListBean.ResultBean.DataBean> commonAdapter;
    LoadMoreWrapper mLoadMoreWrapper;//加载更多的包装器(传入通用适配器)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        ButterKnife.bind(this);
        initView();
        menuListPresenter = new MenuListPresenter(MenuListActivity.this);
        Intent intent = getIntent();
        cid = intent.getStringExtra(Constants.MENU_CID);
        initMap();
        menuListPresenter.loadMenuList(false, Constants.TYPE_FIRST_LOAD);
    }

    private void initMap() {
        params.put(Constants.MENU_CID,  cid);
        params.put(Constants.RESULT_DATA_TYPE,  Constants.RESULT_DATA_TYPE_VALUE);
        params.put(Constants.REQUEST_DATA_POINT_NUM, Constants.REQUEST_DATA_POINT_NUM_VALUE );
        params.put(Constants.REQUEST_DATA_RANGW_NUM, Constants.REQUEST_DATA_RANGW_NUM_MAX_VALUE);
        params.put(Constants.REQUEST_DATA_FORMAT, Constants.REQUEST_DATA_FORMAT_VALUE);
        params.put(Constants.APP_KEY, Constants.APP_KEY_VALUE);
        menuListPresenter.setParamMap(params);
    }

    private void initView() {
        getSupportActionBar().setTitle(R.string.menu_list_title);
        rv_menus.setLayoutManager(new LinearLayoutManager(MenuListActivity.this));
        rv_menus.setItemAnimator(new DefaultItemAnimator());
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.gplus_colors));
    }

    @Override
    public void showBottom(int lastIndex) {
        rv_menus.scrollToPosition(lastIndex);
    }

    @Override
    public Context getCurContext() {
        return this;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showData(List<MenuListBean.ResultBean.DataBean> dataBeanList, int totalCount) {
        Log.d("MenuListActivity++++",  dataBeanList + "");

        commonAdapter = new CommonAdapter<MenuListBean.ResultBean.DataBean>(this, R.layout.item_menu_list, dataBeanList) {

            @Override
            protected void convert(ViewHolder holder, MenuListBean.ResultBean.DataBean dataBean, int position) {
                holder.setText(R.id.menu_title_tv, dataBean.getTitle());
                holder.setText(R.id.menu_imtro_tv, dataBean.getImtro());
                Glide.with(MenuListActivity.this).load(dataBean.getAlbums().get(0)).diskCacheStrategy(DiskCacheStrategy.ALL).into((ImageView) holder.getView(R.id.menu_album_iv));
            }
        };

        mLoadMoreWrapper = new LoadMoreWrapper(commonAdapter);
        View view = View.inflate(this, R.layout.item_load_more, null);
        //要设置一下的布局参数,因为布局填充到包装器的时候,自己的一些属性会无效
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(mLayoutParams);
        final TextView load_tv = (TextView) view.findViewById(R.id.load_tv);
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.load_progress);
        if(commonAdapter.getItemCount() < totalCount){
            mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    load_tv.setText(R.string.loading);
                    progressBar.setVisibility(View.VISIBLE);
                    menuListPresenter.loadMoreMenu();
                }
            });
        }else{
            load_tv.setText(R.string.load_over);
            progressBar.setVisibility(View.GONE);
        }
        mLoadMoreWrapper.setLoadMoreView(view);
        commonAdapter.setOnItemClickListener(this);
        rv_menus.setAdapter(mLoadMoreWrapper);
    }

    @Override
    public void showInfo(String info) {
        ToastUtil.showToast(this, info);
    }

    @Override
    public void showRefresh() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        showRefresh();
        params.put(Constants.REQUEST_DATA_POINT_NUM, Constants.REQUEST_DATA_POINT_NUM_VALUE);
        menuListPresenter.setParamMap(params);
        menuListPresenter.loadMenuList(true, Constants.TYPE_REFRESH);
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        List<MenuListBean.ResultBean.DataBean> datas = commonAdapter.getDatas();
        Intent intent = new Intent(this, MenuDetailActivity.class);
        intent.putExtra(Constants.MENU_ID, datas.get(position).getId());
        intent.putExtra(Constants.MENU_TITLE, datas.get(position).getTitle());
        intent.putExtra(Constants.MENU_ALBUM_SRC, datas.get(position).getAlbums().get(0));
        ImageView menuAlbumIv = (ImageView) view.findViewById(R.id.menu_album_iv);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(MenuListActivity.this, menuAlbumIv, Constants.SHARE_ELEMENT_IV);
            startActivity(intent, options.toBundle());
        }else{
            startActivity(intent);
        }
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
