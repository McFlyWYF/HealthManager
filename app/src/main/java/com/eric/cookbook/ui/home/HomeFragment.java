package com.eric.cookbook.ui.home;

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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.eric.cookbook.Constants;
import com.eric.cookbook.R;
import com.eric.cookbook.bean.MenuListBean;
import com.eric.cookbook.ui.healthy.HealthyEatingActivity;
import com.eric.cookbook.ui.menudetail.MenuDetailActivity;
import com.eric.cookbook.ui.menufind.MenuFindActivity;
import com.eric.cookbook.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;



public class HomeFragment extends Fragment implements HomeContract.IHomeView, CommonAdapter.OnItemClickListener{
//    @Bind(R.id.toolbar)
    Toolbar toolbar;
//    @Bind(R.id.banner)
//    Banner banner;
//    @Bind(R.id.find_ll)
//    LinearLayoutManager find_ll;
//    @Bind(R.id.health_ll)
//    LinearLayoutManager health_ll;
    private RecyclerView rv_total_menu;
    private Banner banner;
//    Integer[] images = {R.drawable.cherry,R.drawable.orange,R.drawable.grape};
    List<Integer> images;
    HomePresenter homePresenter;
    private int cid = 1;
    private Map<String, String> params = new HashMap<>();


    private CommonAdapter<MenuListBean.ResultBean.DataBean> commonAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homee, container, false);
//        toolbar.setTitle(R.string.app_name);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        toolbar.setTitle("热门菜谱");

//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        initBanner(view);
        rv_total_menu = (RecyclerView) view.findViewById(R.id.rv_total_menu);
        initRecyclerView();
        homePresenter = new HomePresenter(this);
        initMap();
        homePresenter.loadMenuList();

        toolbar.inflateMenu(R.menu.photo);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.camera:
                        Toast.makeText(getActivity(),"拍照",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.photo,menu);
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.camera:
                Toast.makeText(getActivity(),"拍照",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    private void initMap() {
        params.put(Constants.MENU_CID,  String.valueOf(cid));
        params.put(Constants.RESULT_DATA_TYPE,  Constants.RESULT_DATA_TYPE_VALUE);
        params.put(Constants.REQUEST_DATA_POINT_NUM, Constants.REQUEST_DATA_POINT_NUM_VALUE );
        params.put(Constants.REQUEST_DATA_RANGW_NUM, Constants.REQUEST_DATA_RANGW_NUM_MAX_VALUE);
        params.put(Constants.REQUEST_DATA_FORMAT, Constants.REQUEST_DATA_FORMAT_VALUE);
        params.put(Constants.APP_KEY, Constants.APP_KEY_VALUE);
        homePresenter.setParamMap(params);
    }

    private void initRecyclerView() {
        rv_total_menu.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_total_menu.setItemAnimator(new DefaultItemAnimator());
    }

    private void initBanner(View view) {

        banner = (Banner) view.findViewById(R.id.banner);
        images = new ArrayList<Integer>();
        images.add(R.drawable.foodone);
        images.add(R.drawable.foodstwo);
        images.add(R.drawable.foodthree);
        banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
    }

    @Override
    public void showBottom(int lastIndex) {
        rv_total_menu.scrollToPosition(lastIndex);
    }

    @Override
    public Context getCurContext() {
        return getContext();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showData(List<MenuListBean.ResultBean.DataBean> dataBeans, final int totalCount, final int itemTotalNum) {
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
//        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.load_progress);
        load_more = (TextView) view.findViewById(R.id.tv_load_more);
        //监听点击加载更多事件
        load_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAGS",  commonAdapter.getItemCount() + "---------count");
                Log.d("TAGS",  totalCount + "-------totalCount");
                Log.d("TAGS",  itemTotalNum + "-------itemTotalNum");
                if(commonAdapter.getItemCount() - totalCount < itemTotalNum) {
                    load_more.setText("加载中...");//点击加载更多-->加载中
                    homePresenter.loadMoreMenu();
                }else{
                    cid = cid + 1;
                    params.put(Constants.MENU_CID, String.valueOf(cid));
                    homePresenter.setParamMap(params);
                    load_more.setText("加载中...");
                    homePresenter.loadMoreMenu();
                }
            }
        });
        mLoadMoreWrapper.setLoadMoreView(view);
        commonAdapter.setOnItemClickListener(this);
        rv_total_menu.setAdapter(mLoadMoreWrapper);
    }

    @Override
    public void showInfo(String info) {
        ToastUtil.showToast(getContext(), info);
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


    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

            //Picasso 加载图片简单用法
//            Picasso.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
//            Uri uri = Uri.parse((String) path);
//            imageView.setImageURI(uri);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
        @Override
        public ImageView createImageView(Context context) {
            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
            ImageView imageView = new ImageView(context);
            return imageView;
        }
    }

    @OnClick({R.id.find_ll, R.id.health_ll})
    public void toOtherActivity(View view){
        switch (view.getId()){
            case R.id.find_ll:
                startActivity(new Intent(getContext(), MenuFindActivity.class));
                break;
            case R.id.health_ll:
                startActivity(new Intent(getContext(), HealthyEatingActivity.class));
                break;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }
}
