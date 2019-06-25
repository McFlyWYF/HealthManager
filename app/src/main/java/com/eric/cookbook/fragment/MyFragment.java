package com.eric.cookbook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.eric.cookbook.R;


/**
 * Created by 16500 on 2019/5/31.
 */

public class MyFragment extends Fragment implements View.OnTouchListener{

    private CardView headCd,collectionCd,recordCd,settingCd,aboutCd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_person,container,false);
        headCd = view.findViewById(R.id.cardview_head);
        collectionCd = view.findViewById(R.id.cardview_collection);
        recordCd = view.findViewById(R.id.cardview_records);
        settingCd = view.findViewById(R.id.cardview_setting);
        aboutCd = view.findViewById(R.id.cardview_about);
        setListener();
        return view;
    }

    private void setListener() {
        headCd.setOnTouchListener(this);
        collectionCd.setOnTouchListener(this);
        recordCd.setOnTouchListener(this);
        settingCd.setOnTouchListener(this);
        aboutCd.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()){
            case R.id.cardview_head:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        headCd.setCardElevation(0);
                        break;
                    case MotionEvent.ACTION_UP:
                        headCd.setCardElevation(20);
                        break;
                }
                return true;

            case R.id.cardview_collection:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        collectionCd.setCardElevation(0);
                        break;
                    case MotionEvent.ACTION_UP:
                        collectionCd.setCardElevation(20);
                        break;
                }
                return true;

            case R.id.cardview_records:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        recordCd.setCardElevation(0);
                        break;
                    case MotionEvent.ACTION_UP:
                        recordCd.setCardElevation(20);
                        break;
                }
                return true;

            case R.id.cardview_setting:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        settingCd.setCardElevation(0);
                        break;
                    case MotionEvent.ACTION_UP:
                        settingCd.setCardElevation(20);
                        break;
                }
                return true;

            case R.id.cardview_about:
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        aboutCd.setCardElevation(0);
                        break;
                    case MotionEvent.ACTION_UP:
                        aboutCd.setCardElevation(20);
                        break;
                }
                return true;
        }
        return false;
    }
}
