package com.eric.cookbook.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eric.cookbook.R;
import com.eric.cookbook.adapter.StaggeredRecyclerViewAdapter;
import com.eric.cookbook.adapter.StaggeredTwoRecyclerViewAdapter;
import com.wj.refresh.OnRefreshListener;
import com.wj.refresh.PullRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FoodsListActivity extends AppCompatActivity {

    //json
    public String disease;
    public RecyclerView recyclerView;
    public List<Map<String, Object>> list = new ArrayList<>();

    private ImageView collection;

    private static final String TAG = "FoodsListActivity";

    private SwipeRefreshLayout swipeRefreshLayout;

    private PullRefreshLayout pullRefreshLayout;

    String diseaseName;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            pullRefreshLayout.onRefreshComplete();
        }
    };
    private String foodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_list);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.foods_recycler_view);
        //获取数据
        okhttpDate();

        pullRefreshLayout = findViewById(R.id.swipe_refresh_view);

        pullRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onPullDownRefresh() {
                mHandler.sendEmptyMessageDelayed(0, 3000);
            }

            @Override
            public void onPullUpRefresh() {
                mHandler.sendEmptyMessageDelayed(0, 3000);

            }
        });

        Intent intent = getIntent();
        diseaseName = intent.getStringExtra("diseaseNames");

//        initImageBitmaps();
    }

    private void okhttpDate() {
        Log.i("TAG", "---ok---");
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String url = "http://39.105.110.19/disease/" + diseaseName;
                Log.d("TAG",url);
                Request request = new Request.Builder().url(url).build();
                try {
                    Response response = client.newCall(request).execute();
                    disease = response.body().string();
                    //解析
                    jsonJXDate(disease);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void jsonJXDate(String foodNames) {

        if (foodNames != null) {
            try {
                if (foodNames.startsWith("\ufeff")){
                    foodNames = foodNames.substring(1);
                }

                JSONObject jsonObject = new JSONObject(foodNames);
                String resultCode = jsonObject.getString("diseaseVariety");
                if (resultCode.equals(diseaseName)){
                    JSONArray jsonArray = jsonObject.getJSONArray("foods");
                    for (int i = 0;i < jsonArray.length();i++){
                        jsonObject = jsonArray.getJSONObject(i);
                        Map<String,Object> map = new HashMap<>();

                        //获取到json数据中的name
                        String name = jsonObject.getString("foodName");
                        String label = jsonObject.getString("foodIntroduce");
                        String imageurl = jsonObject.getString("foodUrl");
                        String diseaseVariety1 = jsonObject.getString("diseaseVariety");
                        String material1 = jsonObject.getString("foodMaterial");
                        String protein1 = jsonObject.getString("foodProtein");
                        String hot1 = jsonObject.getString("foodHot");

                        //存入map
                        map.put("disease",diseaseVariety1);
                        map.put("name",name);
                        map.put("label",label);
                        map.put("image",imageurl);
                        map.put("material",material1);
                        map.put("protein",protein1);
                        map.put("hot",hot1);

                        list.add(map);
                    }
                }

                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    recyclerView.addItemDecoration(new DividerItemDecoration(FoodsListActivity.this,DividerItemDecoration.VERTICAL));
                    StaggeredTwoRecyclerViewAdapter adapter = new StaggeredTwoRecyclerViewAdapter(list,FoodsListActivity.this);

                    recyclerView.setLayoutManager(new LinearLayoutManager(FoodsListActivity.this));
                    recyclerView.setAdapter(adapter);
                    break;

            }
            super.handleMessage(msg);
        }
    };


//    private void initImageBitmaps() {
//
//        for (int i = 0; i < 20; i++) {
//            mImageUrls.add("http://39.105.110.19/static/images/心肌病/3.jpg");
////            mNames.add("牛肉拌花生米");
////            mLabels.add("微辣、拌");
//        }
//
////        initRecyclerView();
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private void initRecyclerView() {
//        Log.d(TAG, "initRecyclerView: initializing staggered recyclerview.");
//
//        RecyclerView recyclerView = findViewById(R.id.foods_recycler_view);
//        StaggeredTwoRecyclerViewAdapter staggeredRecyclerViewAdapter =
//                new StaggeredTwoRecyclerViewAdapter(this, mNames, mImageUrls, mLabels);
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);
//        recyclerView.setAdapter(staggeredRecyclerViewAdapter);
//    }


}
