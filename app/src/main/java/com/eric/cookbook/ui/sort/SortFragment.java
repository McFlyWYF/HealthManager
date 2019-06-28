package com.eric.cookbook.ui.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.eric.cookbook.R;
import com.eric.cookbook.adapter.CategoryAdapter;
import com.eric.cookbook.adapter.CategoryItemAdapter;
import com.eric.cookbook.bean.CookCategory;
import com.eric.cookbook.network.Networks;
import com.eric.cookbook.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



public class SortFragment extends Fragment {

    //    @Bind(R.id.toolbar)
    private Toolbar toolbar;

    private ListView parent_list;

    private RecyclerView child_list;
    private StaggeredGridLayoutManager linearLayoutManager;

    private CategoryAdapter categoryAdapter;
    private CategoryItemAdapter categoryItemAdapter;
    private List<CookCategory.ResultBean> categoryResultBeens;
    private List<CookCategory.ResultBean.ListBean> categoryItemBeans;
    List<CookCategory.ResultBean> itemResultBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("TAG", "onCreateView()");
        View view = inflater.inflate(R.layout.fragment_sort, container, false);
        toolbar = view.findViewById(R.id.toolbar);
//        toolbar.setTitle("分类列表");
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        parent_list = view.findViewById(R.id.parent_list);
        child_list = view.findViewById(R.id.child_list);
        initDatas();

        linearLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        categoryAdapter = new CategoryAdapter(getActivity(), categoryResultBeens);
        parent_list.setAdapter(categoryAdapter);

        categoryItemAdapter = new CategoryItemAdapter(getActivity(), itemResultBean);

        child_list.setLayoutManager(linearLayoutManager);
        child_list.setAdapter(categoryItemAdapter);

        parent_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CookCategory.ResultBean resultBean = categoryResultBeens.get(position);
                itemResultBean.clear();
                itemResultBean.add(resultBean);
                categoryAdapter.setCurrentItem(position);
                parent_list.smoothScrollToPositionFromTop(position, (parent.getHeight() - view.getHeight()) / 2);
                categoryAdapter.notifyDataSetChanged();
                categoryItemAdapter.notifyDataSetChanged();
            }
        });
        ButterKnife.bind(this, view);
        return view;
    }

    public void showMessage(String info) {
        ToastUtil.showToast(getContext(), info);
    }

    private void initDatas() {
        categoryResultBeens = new ArrayList<>();
        categoryItemBeans = new ArrayList<>();
        itemResultBean = new ArrayList<>();

        Networks.getCookApi()
                .getCookCategory(Networks.getCacheControl())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CookCategory>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                showMessage("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            showMessage("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            showMessage("网络连接超时!!");
                        } else {
                            showMessage("发生未知错误" + e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(CookCategory cookCategory) {
                        Log.e("TAG", "CookCategory" + cookCategory.getResult());
                        categoryResultBeens.addAll(cookCategory.getResult());
                        categoryItemBeans.addAll(categoryResultBeens.get(0).getList());
                        itemResultBean.add(categoryResultBeens.get(0));
                        categoryAdapter.setCurrentItem(0);
                        categoryAdapter.notifyDataSetChanged();
                        categoryItemAdapter.notifyDataSetChanged();
                    }
                });
    }
}
