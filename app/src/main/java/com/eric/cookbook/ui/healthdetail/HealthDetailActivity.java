package com.eric.cookbook.ui.healthdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.eric.cookbook.Constants;
import com.eric.cookbook.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/5.
 */

public class HealthDetailActivity extends AppCompatActivity implements HealthDetailContract.IHealthDetailView {
    @Bind(R.id.progress)
    ProgressBar progressBar;
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private String title;
    private String linkTo;
    HealthDetailPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_detail);
        ButterKnife.bind(this);
        initToolbar();
        presenter = new HealthDetailPresenter(this);


        getMessage();
        initWebView();
        Log.e("linkTo-------", linkTo + "++++++++++");
//        presenter.setHealthId(linkTo);
        presenter.loadHealthData();
    }

    private void initToolbar() {
        title = getIntent().getStringExtra(Constants.YSJK_ITEM_TITLE_TEXT);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initWebView() {
        WebSettings settings = webView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDefaultFontSize(16);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
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
    public void showData(String result) {
        webView.loadData(result, "text/html; charset=UTF-8", null);
    }

    @Override
    public void showMessage(String msg) {

    }

    public void getMessage() {
        Intent intent = getIntent();
        linkTo = intent.getStringExtra(Constants.YSJK_ITEM_LINK);
        title = intent.getStringExtra(Constants.YSJK_ITEM_TITLE_TEXT);
        StringBuffer sb = new StringBuffer(linkTo);
        sb.replace(0, Constants.HEALTH_YINGYANG_URL.length(), "");
        Log.e("SB ===",  sb + "");
        linkTo = sb.toString();
        presenter.setHealthId(linkTo);
    }
}
