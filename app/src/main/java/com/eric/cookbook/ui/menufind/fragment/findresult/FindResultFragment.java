package com.eric.cookbook.ui.menufind.fragment.findresult;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/11.
 */

public class FindResultFragment extends Fragment implements FindResultContract.IFindResultView, MultiItemTypeAdapter.OnItemClickListener{

    private String mContent;
    public static final String ARGUMENT = "argument";
    private Map<String, String> params = new HashMap<>();
    private FindResultPresenter findResultPresenter;
    private CommonAdapter<MenuListBean.ResultBean.DataBean> commonAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    @Bind(R.id.rv_menus)
    RecyclerView rv_result_menus;
    /**
     * 传入需要的参数，设置给ARGUMENT
     * @param content
     * @return
     */
    public static FindResultFragment getInstance(String content){
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, content);
        FindResultFragment findResultFragment = new FindResultFragment();
        findResultFragment.setArguments(bundle);
        return findResultFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            mContent = bundle.getString(ARGUMENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_result, container, false);
        ButterKnife.bind(this, view);
        findResultPresenter = new FindResultPresenter(this);
        initRecyclerView();
        initMap();
        findResultPresenter.loadMenuResult();
        return view;
    }

    private void initRecyclerView() {
        rv_result_menus.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_result_menus.setItemAnimator(new DefaultItemAnimator());
    }


    private void initMap() {
        params.put(Constants.MENU_SEARCH_CONTENT,  mContent);
        params.put(Constants.RESULT_DATA_TYPE,  Constants.RESULT_DATA_TYPE_VALUE);
        params.put(Constants.REQUEST_DATA_POINT_NUM, Constants.REQUEST_DATA_POINT_NUM_VALUE );
        params.put(Constants.REQUEST_DATA_RANGW_NUM, Constants.REQUEST_DATA_RANGW_NUM_MAX_VALUE);
        params.put(Constants.MENU_ALBUMS, Constants.MENU_ALBUMS_VALUE);
        params.put(Constants.MENU_BLANK, Constants.MENU_BLANK_VALUE);
        params.put(Constants.APP_KEY, Constants.APP_KEY_VALUE);
        findResultPresenter.setParamMap(params);
    }

    @Override
    public Context getCurContext() {
        return getContext();
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
    public void setResultView(List<MenuListBean.ResultBean.DataBean> dataBeans, final int totalCount) {
        commonAdapter = new CommonAdapter<MenuListBean.ResultBean.DataBean>(getContext(), R.layout.item_menu_list, dataBeans) {

            @Override
            protected void convert(ViewHolder holder, MenuListBean.ResultBean.DataBean dataBean, int position) {
                holder.setText(R.id.menu_title_tv, dataBean.getTitle());
                holder.setText(R.id.menu_imtro_tv, dataBean.getImtro());
                Glide.with(getContext()).load(dataBean.getAlbums().get(0)).diskCacheStrategy(DiskCacheStrategy.ALL).into((ImageView) holder.getView(R.id.menu_album_iv));
            }
        };

        mLoadMoreWrapper = new LoadMoreWrapper(commonAdapter);
        View view = View.inflate(getContext(), R.layout.load_more, null);
        //要设置一下的布局参数,因为布局填充到包装器的时候,自己的一些属性会无效
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(mLayoutParams);
        final TextView load_more;
        load_more = (TextView) view.findViewById(R.id.tv_load_more);
        //监听点击加载更多事件
        load_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAGS",  commonAdapter.getItemCount() + "---------count");
                Log.d("TAGS",  totalCount + "-------totalCount");
                if(commonAdapter.getItemCount() < totalCount) {
                    load_more.setText(R.string.loading);//点击加载更多-->加载中
                    findResultPresenter.loadMoreResult();
                }else{
                    load_more.setText(R.string.load_over);
                }
            }
        });
        mLoadMoreWrapper.setLoadMoreView(view);
        commonAdapter.setOnItemClickListener(this);
        rv_result_menus.setAdapter(mLoadMoreWrapper);
    }

    @Override
    public void showInfo(String info) {
        ToastUtil.showToast(getContext(), info);
    }

    @Override
    public void showBottom(int index) {
        rv_result_menus.scrollToPosition(index);
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        List<MenuListBean.ResultBean.DataBean> datas = commonAdapter.getDatas();
        Intent intent = new Intent(getContext() , MenuDetailActivity.class);
        intent.putExtra(Constants.MENU_ID, datas.get(position).getId());
        intent.putExtra(Constants.MENU_TITLE, datas.get(position).getTitle());
        intent.putExtra(Constants.MENU_ALBUM_SRC, datas.get(position).getAlbums().get(0));
        ImageView menuAlbumIv = (ImageView) view.findViewById(R.id.menu_album_iv);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(getActivity() , menuAlbumIv, Constants.SHARE_ELEMENT_IV);
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
