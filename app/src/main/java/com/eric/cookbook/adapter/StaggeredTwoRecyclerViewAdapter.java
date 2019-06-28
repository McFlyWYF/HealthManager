package com.eric.cookbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eric.cookbook.activity.FoodInfoActivity;
import com.eric.cookbook.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 16500 on 2019/6/22.
 */

public class StaggeredTwoRecyclerViewAdapter extends RecyclerView.Adapter<StaggeredTwoRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "StaggeredTwoRecyclerViewAdapter";

    private boolean isChanged = false;

    private Context mContext;
    private List<Map<String,Object>> list = new ArrayList<>();
    private LayoutInflater inflater;

    public StaggeredTwoRecyclerViewAdapter(List<Map<String, Object>> list, Context context){
        mContext = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


//    public StaggeredTwoRecyclerViewAdapter(Context context,ArrayList<Map<String,Object>> names,ArrayList<String> imagesUrl,ArrayList<Map<String,Object>> label){
//        mNames = names;
//        mImageUrl = imagesUrl;
//        mLabels = label;
//        mContext = context;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.foods_recyclerview,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        Glide.with(mContext)
//                .load(mImageUrl.get(position))
//                .into(holder.image);
        holder.recy_name.setText(list.get(position).get("name").toString());
        holder.recy_label.setText(list.get(position).get("label").toString());
        String url = list.get(position).get("image").toString();

        final Bundle bundle = new Bundle();
        bundle.putString("foodInfo1",list.get(position).get("disease").toString());
        bundle.putString("foodInfo2",list.get(position).get("name").toString());
        bundle.putString("foodInfo3",list.get(position).get("image").toString());
        bundle.putString("foodInfo4",list.get(position).get("material").toString());
        bundle.putString("foodInfo5",list.get(position).get("protein").toString());
        bundle.putString("foodInfo6",list.get(position).get("hot").toString());


        Log.d("foodInfo1",list.get(position).get("disease").toString());
        Log.d("foodInfo2",list.get(position).get("name").toString());


        Glide.with(mContext)
                .load(url)
                .into(holder.recy_image);


        holder.recy_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position){
                    case 0:
                        Intent intent0 = new Intent(mContext,FoodInfoActivity.class);
                        intent0.putExtras(bundle);
                        mContext.startActivity(intent0);
                    case 1:
                        Intent intent1 = new Intent(mContext, FoodInfoActivity.class);
                        intent1.putExtras(bundle);
                        mContext.startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(mContext, FoodInfoActivity.class);
                        intent2.putExtras(bundle);
                        mContext.startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(mContext,FoodInfoActivity.class);
                        intent3.putExtras(bundle);
                        mContext.startActivity(intent3);
                    case 4:
                        Intent intent4 = new Intent(mContext, FoodInfoActivity.class);
                        intent4.putExtras(bundle);
                        mContext.startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(mContext,FoodInfoActivity.class);
                        intent5.putExtras(bundle);
                        mContext.startActivity(intent5);
                    case 6:
                        Intent intent6 = new Intent(mContext, FoodInfoActivity.class);
                        intent6.putExtras(bundle);
                        mContext.startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent(mContext,FoodInfoActivity.class);
                        intent7.putExtras(bundle);
                        mContext.startActivity(intent7);
                    case 8:
                        Intent intent8 = new Intent(mContext, FoodInfoActivity.class);
                        intent8.putExtras(bundle);
                        mContext.startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9 = new Intent(mContext,FoodInfoActivity.class);
                        intent9.putExtras(bundle);
                        mContext.startActivity(intent9);
                    case 10:
                        Intent intent10 = new Intent(mContext, FoodInfoActivity.class);
                        intent10.putExtras(bundle);
                        mContext.startActivity(intent10);
                        break;
                }
            }
        });

        holder.recy_collection.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if (view == holder.recy_collection){
                    if(isChanged){
                        holder.recy_collection.setImageDrawable(mContext.getResources().getDrawable(R.drawable.fooduncollection));
                        Toast.makeText(mContext,"取消收藏",Toast.LENGTH_SHORT).show();
                    }else
                    {
                        holder.recy_collection.setImageDrawable(mContext.getResources().getDrawable(R.drawable.foodcollection));
                        Toast.makeText(mContext,"已收藏",Toast.LENGTH_SHORT).show();
                    }
                    isChanged = !isChanged;

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView recy_image;
        TextView recy_name;
        TextView recy_label;

        ImageView recy_collection;

        public ViewHolder(View itemView) {
            super(itemView);
            recy_image = itemView.findViewById(R.id.foods_imageview);
            recy_name = itemView.findViewById(R.id.foods_text);
            recy_label = itemView.findViewById(R.id.foods_label);
            recy_collection = itemView.findViewById(R.id.foods_collection);
        }
    }

}
