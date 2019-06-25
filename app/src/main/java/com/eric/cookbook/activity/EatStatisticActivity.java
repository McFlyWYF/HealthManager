package com.eric.cookbook.activity;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eric.cookbook.R;
import com.lwb.piechart.PieChartView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EatStatisticActivity extends AppCompatActivity implements View.OnClickListener {

    OkHttpClient client = new OkHttpClient();
    private EditText protein_et, hot_et, sugar_et;

    private Button submit;
    String time;
    String result;

    private String protein;
    private String hot;
    private String sugar;

    Handler eathandler;
    private boolean eatFlag;

    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        TextView eatTimeTv;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat_statistic);

        eatTimeTv = findViewById(R.id.eat_time_tv);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        time = sdf.format(date);
        eatTimeTv.setText(time + "摄入能量统计");

        protein_et = findViewById(R.id.protein_edt);
        hot_et = findViewById(R.id.hot_edt);
        sugar_et = findViewById(R.id.sugar_edt);
        submit = findViewById(R.id.commit_btn);

        submit.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initChartView();

        eathandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                eatFlag = msg.getData().getBoolean("eatFlag");
                if (eatFlag) {
                    Toast.makeText(EatStatisticActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EatStatisticActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        eathandler.removeCallbacksAndMessages(null);
    }

    class EatHandler implements Runnable {
        @Override
        public void run() {
            protein = protein_et.getText().toString();
            hot = hot_et.getText().toString();
            sugar = sugar_et.getText().toString();
            String url = "http://39.105.110.19/eatS/";
            boolean eatState = validatelocaleat(protein, hot, sugar, time, url);
            Message message = new Message();
            Bundle bundle = new Bundle();
            if (eatState) {
                bundle.putBoolean("eatFlag", true);
                message.setData(bundle);
                eathandler.sendMessage(message);
            } else {
                bundle.putBoolean("eatFlag", false);
                message.setData(bundle);
                eathandler.sendMessage(message);

            }
        }
    }

    private boolean validatelocaleat(String protein, String hot, String sugar, String time, String url) {
        Log.d("protein", protein);
        Log.d("hot", hot);
        Log.d("sugar", sugar);

        boolean signState = false;
        HttpPost httpRequest = new HttpPost(url);
        List params = new ArrayList<>();
        params.add(new BasicNameValuePair("protein", protein));
        params.add(new BasicNameValuePair("hot", hot));
        params.add(new BasicNameValuePair("sugar", sugar));
        params.add(new BasicNameValuePair("time", time));

        try {
            httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            HttpClient httpClient = new DefaultHttpClient();
            HttpParams httpParams = httpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
            HttpConnectionParams.setSoTimeout(httpParams, 5000);
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            httpResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
            String strResult = EntityUtils.toString(httpResponse.getEntity());
            JSONObject object = new JSONObject(strResult);
            String success = object.getString("message");
            Log.d("message", success);
            if (success.equals("存入成功")) {
                signState = true;
            } else {
                signState = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signState;
    }


    private void initChartView() {

        PieChartView pieChartView = findViewById(R.id.pie_chart_view);
        pieChartView.addItemType(new PieChartView.ItemType("蛋白质", 12, 0xffE6B800));
        pieChartView.addItemType(new PieChartView.ItemType("热量", 63, 0xffFF9069));
        pieChartView.addItemType(new PieChartView.ItemType("糖分", 10, 0xff6495ED));
        pieChartView.addItemType(new PieChartView.ItemType("其他", 15, 0xffE32636));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showNormalDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(EatStatisticActivity.this);
        builder.setIcon(R.drawable.success);
        builder.setTitle("消息提示");
        builder.setMessage("提交成功");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {

                    Field field = builder.getClass().getDeclaredField("mShowing");
                    field.setAccessible(true);
                    field.set(builder, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        builder.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.commit_btn:
                if (protein_et.getText().toString().trim().isEmpty() || hot_et.getText().toString().trim().isEmpty() || sugar_et.getText().toString().trim().isEmpty()) {
                    Toast.makeText(EatStatisticActivity.this, "请输入完整的信息", Toast.LENGTH_SHORT).show();
                    protein_et.setText("");
                    hot_et.setText("");
                    sugar_et.setText("");
                    showFailedDialog();
                } else {
                    Thread signThread = new Thread(new EatHandler());
                    signThread.start();
                    showNormalDialog();
                    protein_et.setText("");
                    hot_et.setText("");
                    sugar_et.setText("");
                }

        }

    }

    private void showFailedDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(EatStatisticActivity.this);
        builder.setIcon(R.drawable.success);
        builder.setTitle("消息提示");
        builder.setMessage("提交失败");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {

                    Field field = builder.getClass().getDeclaredField("mShowing");
                    field.setAccessible(true);
                    field.set(builder, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        builder.show();
    }
}