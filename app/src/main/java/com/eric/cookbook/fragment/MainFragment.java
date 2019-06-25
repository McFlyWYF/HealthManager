package com.eric.cookbook.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.eric.cookbook.activity.EatStatisticActivity;
import com.eric.cookbook.R;
import com.eric.cookbook.activity.FoodsRecommendActivity;
import com.eric.cookbook.step.StepService;
import com.eric.cookbook.step.UpdateUiCallBack;
import com.eric.cookbook.view.WaveProgress;

import java.lang.reflect.Field;

import butterknife.Bind;

/**
 * Created by 16500 on 2019/5/31.
 */

public class MainFragment extends Fragment implements View.OnClickListener {


    @Bind(R.id.toolbar_main)
    Toolbar toolbar;

    private final static int[] COLORS = new int[]{111, Color.YELLOW, Color.GREEN, Color.BLUE};
    private WaveProgress waveProgress;

    private String steps;

    private TextView stepsView, calorieView, metersView,stepssView;

    private ImageButton setSteps, heartRate, eatStatistic, recommendFoods;


    private StepService mService;
    private boolean mIsRunning;
    private SharedPreferences mySharedPreferences;
    private int calorie, meters;

    private Float waveCount;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            meters = Integer.valueOf(mySharedPreferences.getString("steps", "0"));
            calorie = (int) (60 * meters * 0.8214);
            waveCount = Float.valueOf(mySharedPreferences.getString("steps", "0"));
            waveCount = waveCount / 10;

            if (msg.what == 1) {
                stepsView.setText(mySharedPreferences.getString("steps", "0"));
                calorieView.setText("卡路里：" + calorie + "卡");
                metersView.setText("里程数：" + meters + "M");
                waveProgress.setValue(waveCount);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        stepsView = view.findViewById(R.id.current_steps);
        calorieView = view.findViewById(R.id.calorie);
        metersView = view.findViewById(R.id.meters);
        stepssView = view.findViewById(R.id.aim_steps);

        waveProgress = view.findViewById(R.id.wave_progress_bar);
        waveProgress.setValue(0);
        setSteps = view.findViewById(R.id.set_steps_btn);
        heartRate = view.findViewById(R.id.heart_rate_btn);
        eatStatistic = view.findViewById(R.id.eat_statistic_btn);
        recommendFoods = view.findViewById(R.id.recommend_foods_btn);

        setSteps.setOnClickListener(this);
        heartRate.setOnClickListener(this);
        eatStatistic.setOnClickListener(this);
        recommendFoods.setOnClickListener(this);


//        UltraViewPager ultraViewPager = (UltraViewPager)view.findViewById(R.id.ultra_viewpager);
//        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
//        PagerAdapter adapter = new UltraPagerAdapter(false);
//        ultraViewPager.setAdapter(adapter);
//        ultraViewPager.setMultiScreen(0.5f);
//        ultraViewPager.setItemRatio(1.0f);
//        ultraViewPager.setAutoMeasureHeight(true);
//
//        ultraViewPager.initIndicator();
//        ultraViewPager.getIndicator().build();
//
//        ultraViewPager.setInfiniteLoop(true);
//        ultraViewPager.setAutoScroll(2000);
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mySharedPreferences = getActivity().getSharedPreferences("relevant_data", Activity.MODE_PRIVATE);
        startStepService();//启动统计步数服务
        super.onCreate(savedInstanceState);
    }


    public void onDestroy() {
        super.onDestroy();
    }

    public void onPause() {
        unbindStepService();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        stepsView.setText(mySharedPreferences.getString("steps", "0"));
        if (this.mIsRunning) {
            bindStepService();
        }
    }

    private UpdateUiCallBack mUiCallback = new UpdateUiCallBack() {
        @Override
        public void updateUi() {
            Message message = mHandler.obtainMessage();
            message.what = 1;
            mHandler.sendMessage(message);
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StepService.StepBinder binder = (StepService.StepBinder) service;
            mService = binder.getService();
            mService.registerCallback(mUiCallback);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void bindStepService() {
        getActivity().bindService(new Intent(getActivity(), StepService.class), this.mConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbindStepService() {
        getActivity().unbindService(this.mConnection);
    }

    private void startStepService() {
        this.mIsRunning = true;
        getActivity().startService(new Intent(getActivity(), StepService.class));
    }

    private void stopStepService() {
        this.mIsRunning = false;
        if (this.mService != null)
            getActivity().stopService(new Intent(getActivity(), StepService.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.set_steps_btn:
                showDialog();
                break;
            case R.id.heart_rate_btn:
                Intent intent = new Intent(getActivity(), HeartRateActivity.class);
                startActivity(intent);
                break;
            case R.id.eat_statistic_btn:
                Intent intent1 = new Intent(getActivity(), EatStatisticActivity.class);
                startActivity(intent1);
                break;
            case R.id.recommend_foods_btn:
                Intent intent2 = new Intent(getActivity(), FoodsRecommendActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }

    }

    private void showDialog() {

        final EditText editText = new EditText(getActivity());
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("设置目标步数").setView(editText);
        builder.setIcon(R.drawable.canshi);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                steps = editText.getText().toString();
                stepssView.setText("目标步数："+steps);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {

                    Field field = builder.getClass().getDeclaredField("mShowing");
                    field.setAccessible(true);
                    field.set(builder, true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        builder.show();
    }
}