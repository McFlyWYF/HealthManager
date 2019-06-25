package com.eric.cookbook.ui.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.eric.cookbook.Constants;
import com.eric.cookbook.R;
import com.eric.cookbook.ui.healthy.fragment.HealthyEatingFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/15.
 */

public class HealthyEatingActivity extends AppCompatActivity {

    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.vp_view)
    ViewPager viewPager;

    private String mTitles[] = new String[]{"最新","最热"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_eating);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position == 1){
                    return new HealthyEatingFragment(Constants.YSJK_TYPE_HOT);
                }
                return new HealthyEatingFragment(Constants.YSJK_TYPE_LATEST);
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
        tabs.setupWithViewPager(viewPager);
    }
}
