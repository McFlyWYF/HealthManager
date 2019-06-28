package com.eric.cookbook.activity;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eric.cookbook.R;
import com.eric.cookbook.adapter.StaggeredTwoRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FoodInfoActivity extends AppCompatActivity {

    private TextView name_tv, material_tv, protein_tv, hot_tv;
    private ImageView image_iv;

    public List<Map<String, Object>> list = new ArrayList<>();
    String foodInfo;
    String foodInfo1;

    String food;

    private String name, material, image, hot, protein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        name_tv = findViewById(R.id.food_name_tv);
        material_tv = findViewById(R.id.food_material_tv);
        protein_tv = findViewById(R.id.food_protein_tv);
        hot_tv = findViewById(R.id.food_hot_tv);
        image_iv = findViewById(R.id.food_image_iv);

        Intent intent = getIntent();
        name = intent.getStringExtra("foodInfo2");
        material = intent.getStringExtra("foodInfo4");
        protein = intent.getStringExtra("foodInfo5");
        hot = intent.getStringExtra("foodInfo6");
        image = intent.getStringExtra("foodInfo3");

        Log.d("intent", name);
        Log.d("intent", material);
        Log.d("intent", protein);
        Log.d("intent", hot);
        Log.d("intent", image);

        name_tv.setText(name);
        material_tv.setText(material);
        protein_tv.setText("蛋白质：" + protein + " 克");
        hot_tv.setText("热量：" + hot + " J");

        Glide.with(this)
                .load(image)
                .into(image_iv);

//        okhttpDate();

//        init();
    }

//    private void init() {
//        name_tv.setText(name.toString());
//        material_tv.setText(material.toString());
//        protein_tv.setText(protein.toString());
//        hot_tv.setText(hot.toString());
//
//        String url = imageurl.toString();
//
//        Glide.with(this)
//                .load(url)
//                .into(image_iv);
//    }

//    private void okhttpDate() {
//        Log.i("TAG", "---ok---");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient client = new OkHttpClient();
//                String url = "http://39.105.110.19/disease/" + foodInfo;
//                Log.d("TAG-url", url);
//                Request request = new Request.Builder().url(url).build();
//                try {
//                    Response response = client.newCall(request).execute();
//                    food = response.body().string();
//                    jsonJXDate(food);
//                    Log.d("food",food);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }

//    private void jsonJXDate(String food) {
//
//        if (food != null) {
//            try {
//                if (food.startsWith("\ufeff")) {
//                    food = food.substring(1);
//                }
//
//                JSONObject jsonObject = new JSONObject(food);
//                String resultCode = jsonObject.getString("diseaseVariety");
//                if (resultCode.equals(food)) {
//                    JSONArray jsonArray = jsonObject.getJSONArray("foods");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        jsonObject = jsonArray.getJSONObject(i);
//                        Map<String, Object> map = new HashMap<>();
//
//                        //获取到json数据中的name
//                        name = jsonObject.getString("foodName");
//                        material = jsonObject.getString("foodMaterial");
//                        imageurl = jsonObject.getString("foodUrl");
//                        hot = jsonObject.getString("foodHot");
//                        protein = jsonObject.getString("foodProtein");
//
//                        map.put("foodname",name);
//                        map.put("foodmaterial",material);
//                        map.put("foodurl",imageurl);
//                        map.put("fooodhot",hot);
//                        map.put("foodprotein",protein);
//
//                        list.add(map);
//                    }
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
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
}
