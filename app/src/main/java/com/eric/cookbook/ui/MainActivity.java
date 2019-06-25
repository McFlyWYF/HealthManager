package com.eric.cookbook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.eric.cookbook.R;
import com.eric.cookbook.fragment.MainFragment;
import com.eric.cookbook.ui.home.HomeFragment;
import com.eric.cookbook.ui.mine.MineFragment;
import com.eric.cookbook.ui.sort.SortFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.navigation)
    BottomNavigationView navigationView;
    private MenuItem menuItem;
    private List<Fragment> fragments;
    public static final long TIME_SPACE = 2000;
    private long clickTime;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_heartRate:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_hotMenu:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.ic_mine:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }

    };
    private ViewPager upViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDatas();
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    navigationView.getMenu().getItem(0).setChecked(false);
                }
                navigationView.getMenu().getItem(position).setChecked(true);
                menuItem = navigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new VpAdapter(getSupportFragmentManager(), fragments));
    }

    private void initDatas() {
        fragments = new ArrayList<>(4);

        // add fragments
        fragments.add(new MainFragment());
        fragments.add(new HomeFragment());
        fragments.add(new SortFragment());
        fragments.add(new MineFragment());
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        public VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                exit();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > TIME_SPACE) {
            Toast.makeText(getApplicationContext(), R.string.exit_if_repeat,
                    Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
            return;
        }
        finish();
    }
}