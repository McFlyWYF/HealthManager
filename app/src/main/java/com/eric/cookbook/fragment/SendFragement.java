package com.eric.cookbook.fragment;


import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.eric.cookbook.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by 16500 on 2019/7/4.
 */

public class SendFragement extends Fragment implements View.OnClickListener {

    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.send, container, false);
        Button btnSendMessage = view.findViewById(R.id.btn_send_message);
        btnSendMessage.setOnClickListener(this);
        btnSendMessage.getBackground().setAlpha(150);//设置透明度
        Button btnSetMessage = view.findViewById(R.id.btn_set_message);
        btnSetMessage.setOnClickListener(this);

        return view;
    }


    public interface SendfraInterface {
        void sendOnClick();

        void setOnClick();
    }


    @Override
    public void onClick(View view) {
        if (getActivity() instanceof SendfraInterface) {
            switch (view.getId()) {
                case R.id.btn_send_message:
                    ((SendfraInterface) getActivity()).sendOnClick();

                    break;
                case R.id.btn_set_message:
                    ((SendfraInterface) getActivity()).setOnClick();
                    break;
                default:
                    break;
            }
        }
    }

}
