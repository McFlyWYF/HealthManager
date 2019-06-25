package com.eric.cookbook.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;

import com.eric.cookbook.R;
import com.eric.cookbook.adapter.StaggeredRecyclerViewAdapter;

import java.util.ArrayList;

public class FoodsRecommendActivity extends AppCompatActivity {

    private static final String TAG = "FoodsRecommendActivity";
    private static final int NUM_COLUMNS = 2;

    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();

    private StaggeredRecyclerViewAdapter adapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_recommend);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initImageBitmaps();

    }


    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("http://39.105.110.19/static/images/心肌病/1.jpg");
        mNames.add("心肌病");

        mImageUrls.add("http://39.105.110.19/static/images/急性冠状动脉综合征/1.jpg");
        mNames.add("急性冠状动脉综合征");

        mImageUrls.add("http://39.105.110.19/static/images/风湿性心脏瓣膜病/1.jpg");
        mNames.add("风湿性心脏瓣膜病");

        mImageUrls.add("http://39.105.110.19/static/images/感染性心肌炎/1.jpg");
        mNames.add("感染性心肌炎");


        mImageUrls.add("http://39.105.110.19/static/images/心脏内粘液瘤/1.jpg");
        mNames.add("心脏内粘液瘤");

        mImageUrls.add("http://39.105.110.19/static/images/高血压/1.jpg");
        mNames.add("高血压");

        mImageUrls.add("http://39.105.110.19/static/images/情绪心脏病/1.jpg");
        mNames.add("情绪心脏病");

        mImageUrls.add("http://39.105.110.19/static/images/妊娠合并心脏病/1.jpg");
        mNames.add("妊娠合并心脏病");

        mImageUrls.add("http://39.105.110.19/static/images/急性心房心肌梗塞/1.jpg");
        mNames.add("急性心房心肌梗塞");

        mImageUrls.add("http://39.105.110.19/static/images/心脏性猝死/1.jpg");
        mNames.add("心脏性猝死");

        mImageUrls.add("http://39.105.110.19/static/images/穿透性心脏外伤/1.jpg");
        mNames.add("穿透性心脏外伤");
        initRecyclerView();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: initializing staggered recyclerview.");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        StaggeredRecyclerViewAdapter staggeredRecyclerViewAdapter =
                new StaggeredRecyclerViewAdapter(this, mNames, mImageUrls);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(staggeredRecyclerViewAdapter);
    }

}
