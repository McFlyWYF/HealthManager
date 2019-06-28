package com.eric.cookbook.ui.menufind;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eric.cookbook.R;
import com.eric.cookbook.ui.common.CleanEditText;
import com.eric.cookbook.ui.menufind.fragment.findhistory.FindHistoryFragment;
import com.eric.cookbook.ui.menufind.fragment.findresult.FindResultFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class MenuFindActivity extends AppCompatActivity implements MenuFindContract.IMenuFindView, FindHistoryFragment.OnSearchCallback{

    @Bind(R.id.layout_statusbar)
    LinearLayout layoutStatusbar;
    @Bind(R.id.btn_search_cancel)
    TextView btnSearchCancel;
    @Bind(R.id.et_search)
    CleanEditText etSearch;

    private MenuFindPresenter menuFindPresenter;
    private FindHistoryFragment findHistoryFragment;
    private FindResultFragment findResultFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu_find);
        ButterKnife.bind(this);
        initStatusBar();
        menuFindPresenter = new MenuFindPresenter(this, this);
        initDefaultFragment();
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search(etSearch.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });
    }

    private void initStatusBar() {
        //将手机状态栏透明化，
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    private void initDefaultFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        findHistoryFragment = (FindHistoryFragment) fragmentManager.findFragmentById(R.id.content_container);
        if(findHistoryFragment == null){
            findHistoryFragment = new FindHistoryFragment();
            fragmentManager.beginTransaction().add(R.id.content_container,  findHistoryFragment).commit();
        }
    }

    @OnClick({R.id.btn_search_cancel, R.id.btn_search_check})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search_cancel:
                finish();
                break;
            case R.id.btn_search_check:
                String value = etSearch.getText().toString().trim();
                search(value);
                break;
        }
    }


    public void search(String content) {
        if (!TextUtils.isEmpty(content)) {
            // 先隐藏键盘
            ((InputMethodManager) etSearch.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(MenuFindActivity.this.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
            menuFindPresenter.saveContent(content);
        }
    }

    @Override
    public void replaceFragment(String content) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        findResultFragment = findResultFragment.getInstance(content);
        fragmentManager.beginTransaction().replace(R.id.content_container, findResultFragment).commit();
    }

    @Override
    public void sendSearchContent(String content) {
        etSearch.setText(content);
        etSearch.setSelection(content.length());
        search(content);
    }
}
