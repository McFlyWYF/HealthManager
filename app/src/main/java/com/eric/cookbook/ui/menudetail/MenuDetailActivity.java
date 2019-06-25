package com.eric.cookbook.ui.menudetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.eric.cookbook.Constants;
import com.eric.cookbook.R;
import com.eric.cookbook.adapter.MenuDetailAdapter;
import com.eric.cookbook.bean.MenuListBean;
import com.eric.cookbook.bean.MenuMaterialBean;
import com.eric.cookbook.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2017/4/4.
 */

public class MenuDetailActivity extends AppCompatActivity implements MenuDetailContract.IMenuDetailView {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.menu_detail_album_iv)
    ImageView menu_detail_album_iv;
    @Bind(R.id.step_list_rv)
    RecyclerView step_list_rv;
    @Bind(R.id.menu_imtro)
    TextView menu_imtro;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private String id;
    private String title;
    private Map<String, String> params = new HashMap<>();
    private MenuDetailPresenter menuDetailPresenter;
    private List<MenuMaterialBean> materialBeanList = new ArrayList<>();
    private List<MenuListBean.ResultBean.DataBean.StepsBean> stepsBeanList = new ArrayList<>();
    private MenuDetailAdapter menuDetailAdapter;
    private MenuListBean.ResultBean.DataBean dataBean;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        ButterKnife.bind(this);
        menuDetailAdapter = new MenuDetailAdapter(this);
        Intent intent = getIntent();
        id = intent.getStringExtra(Constants.MENU_ID);
        title = intent.getStringExtra(Constants.MENU_TITLE);
        initView();
        menuDetailPresenter = new MenuDetailPresenter(this);
        initMap();
        menuDetailPresenter.loadMenuDetail();

    }

    private void initView() {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        step_list_rv.setLayoutManager(new LinearLayoutManager(MenuDetailActivity.this));
        step_list_rv.setItemAnimator(new DefaultItemAnimator());
        step_list_rv.setAdapter(menuDetailAdapter);
        fab.setVisibility(View.VISIBLE);
    }

    private void initMap() {
        params.put(Constants.MENU_ID,  id);
        params.put(Constants.RESULT_DATA_TYPE,  Constants.RESULT_DATA_TYPE_VALUE);
        params.put(Constants.APP_KEY, Constants.APP_KEY_VALUE);
        menuDetailPresenter.setParamMap(params);
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
    public void setDetailView(MenuListBean.ResultBean.DataBean dataBeans) {
        this.dataBean = dataBeans;
        menu_imtro.setText(dataBeans.getImtro());
        Glide.with(this).load(dataBeans.getAlbums().get(0))
                .asBitmap()
                .placeholder(R.drawable.ic_loading)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .error(R.drawable.ic_load_fail)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(menu_detail_album_iv);
        initMaterialBean(dataBeans);
        stepsBeanList.addAll(dataBeans.getSteps());
        menuDetailAdapter.addList(stepsBeanList, materialBeanList);
        menuDetailAdapter.notifyDataSetChanged();
    }

    private void initMaterialBean(MenuListBean.ResultBean.DataBean dataBeans) {
        String ingredients = dataBeans.getIngredients();
        String burden = dataBeans.getBurden();
        String menuMaterial = ingredients + ";" + burden;
        String[] ss = menuMaterial.split(";");

        for(int i = 0;  i <  ss.length; i++){
            String[] splitArr = null;
            splitArr = ss[i].split(",");
            MenuMaterialBean menuMaterialBean = new MenuMaterialBean();
            menuMaterialBean.setMaterialName(splitArr[0]);
            menuMaterialBean.setMaterialNum(splitArr[1]);
            materialBeanList.add(menuMaterialBean);
        }
    }

    @OnClick(R.id.fab)
    public void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();
// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(title);
// titleUrl是标题的网络链接，QQ和QQ空间等使用
//        oks.setTitleUrl(url);
// text是分享文本，所有平台都需要这个字段

        String shareContent = dataBean.getImtro();
        StringBuffer sb = new StringBuffer(shareContent);
        sb.append("\n").append(dataBean.getBurden()).append("\n");
        sb.append(dataBean.getIngredients()).append("\n");

        for (int i = 0; i < dataBean.getSteps().size(); i++){
            sb.append(dataBean.getSteps().get(i).getStep()).append("\n");
        }

        oks.setText(sb.toString());
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        oks.setImageUrl(dataBean.getAlbums().get(0));
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("www.baidu.com");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("www.baidu.com");
// 启动分享GUI
        oks.show(this);
    }

    @Override
    public void showInfo(String info) {
        ToastUtil.showToast(this, info);
    }
}
