package com.eric.cookbook.ui.healthy.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.eric.cookbook.Constants;
import com.eric.cookbook.R;
import com.eric.cookbook.bean.HealthyEatingBean;
import com.eric.cookbook.ui.healthdetail.HealthDetailActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/15.
 */

@SuppressLint("ValidFragment")
public class HealthyEatingFragment extends Fragment implements HealthyEatingContracts.IHealthyEatingView,MultiItemTypeAdapter.OnItemClickListener{

    private int ysjkType = Constants.YSJK_TYPE_LATEST;
    private HealthyEatingPresenter healthyEatingPresenter;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.rv_health_list)
    RecyclerView rv_health_list;

    private CommonAdapter<HealthyEatingBean> commonAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;
    @SuppressLint("ValidFragment")
    public HealthyEatingFragment(int ysjkType) {
        this.ysjkType = ysjkType;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_healthy_eating, container, false);
        ButterKnife.bind(this, view);
        initViews();
        if(ysjkType == Constants.YSJK_TYPE_LATEST){
            healthyEatingPresenter = new HealthyEatingPresenter(this, ysjkType);
        }else{
            healthyEatingPresenter = new HealthyEatingPresenter(this, ysjkType);
        }
        healthyEatingPresenter.loadHealthyData();
        return view;
    }

    private void initViews() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(),  huluobu);
//        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(huluobu,
//                StaggeredGridLayoutManager.VERTICAL);
//        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rv_health_list.setLayoutManager(layoutManager);
        rv_health_list.setItemAnimator(new DefaultItemAnimator());
//        rv_health_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                layoutManager.invalidateSpanAssignments();
//            }
//        });
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
    public void showData(List<HealthyEatingBean> healthyEatingList, int curPage) {
        commonAdapter = new CommonAdapter<HealthyEatingBean>(getContext(), R.layout.item_menu_list, healthyEatingList) {

            @Override
            protected void convert(ViewHolder holder, HealthyEatingBean healthyEatingBean, int position) {
                holder.setText(R.id.menu_title_tv, healthyEatingBean.getHealthTitle());
                holder.setText(R.id.menu_imtro_tv, healthyEatingBean.getHealthContent());
                Glide.with(getContext()).load(healthyEatingBean.getHealthImgSrc()).diskCacheStrategy(DiskCacheStrategy.ALL).into((ImageView) holder.getView(R.id.menu_album_iv));
            }
        };

        mLoadMoreWrapper = new LoadMoreWrapper(commonAdapter);
        View view = View.inflate(getContext(), R.layout.load_more, null);
        //要设置一下的布局参数,因为布局填充到包装器的时候,自己的一些属性会无效
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(mLayoutParams);
        final TextView load_more;
//        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.load_progress);
        load_more = (TextView) view.findViewById(R.id.tv_load_more);
        //监听点击加载更多事件
        load_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAGS",  commonAdapter.getItemCount() + "---------count");
//                Log.d("TAGS",  totalCount + "-------totalCount");
//                Logg.d("TAGS",  itemTotalNum + "-------itemTotalNum");
                healthyEatingPresenter.loadMoreHealthyData();

            }
        });
        mLoadMoreWrapper.setLoadMoreView(view);
        commonAdapter.setOnItemClickListener(this);
        rv_health_list.setAdapter(mLoadMoreWrapper);
    }

    @Override
    public void showBottom(int lastIndex) {
        rv_health_list.scrollToPosition(lastIndex);
    }

    @Override
    public void showInfo(String info) {

    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        Toast.makeText(getContext(),  "健康饮食",  Toast.LENGTH_SHORT);
        String title = commonAdapter.getDatas().get(position).getHealthTitle();
        String linkTo = commonAdapter.getDatas().get(position).getHealthLink();
        Intent intent = new Intent(getContext(), HealthDetailActivity.class);
        intent.putExtra(Constants.YSJK_ITEM_TITLE_TEXT, title);
        intent.putExtra(Constants.YSJK_ITEM_LINK, linkTo);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
