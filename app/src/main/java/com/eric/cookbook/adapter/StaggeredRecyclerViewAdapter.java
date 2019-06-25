package com.eric.cookbook.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eric.cookbook.R;
import com.eric.cookbook.activity.FoodsListActivity;

import java.util.ArrayList;

/**
 * Created by 16500 on 2019/6/21.
 */

public class StaggeredRecyclerViewAdapter extends RecyclerView.Adapter<StaggeredRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "StaggeredRecyclerViewAdapter";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private Context mContext;

    public StaggeredRecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<String> imageUrls) {
        mNames = names;
        mImageUrls = imageUrls;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid_item, parent, false);
        return new ViewHolder(view);
    }

    //    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        Log.d(TAG, "onBindViewHolder: called.");

        final Bundle bundle = new Bundle();
        bundle.putString("diseaseNames", mNames.get(position));

        Glide.with(mContext)
                .load(mImageUrls.get(position))
                .into(holder.image);
        final int pos = holder.getLayoutPosition();
        holder.name.setText(mNames.get(position));

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d(TAG, "onClick: clicked on: " + mNames.get(position));
                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(this, FoodsListActivity.class);
                switch (pos) {
                    case 0:
                        Intent intent0 = new Intent(mContext, FoodsListActivity.class);
                        intent0.putExtras(bundle);
                        mContext.startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(mContext, FoodsListActivity.class);
                        intent1.putExtras(bundle);
                        mContext.startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(mContext, FoodsListActivity.class);
                        intent2.putExtras(bundle);
                        mContext.startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(mContext, FoodsListActivity.class);
                        intent3.putExtras(bundle);

                        mContext.startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(mContext, FoodsListActivity.class);
                        intent4.putExtras(bundle);
                        mContext.startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(mContext, FoodsListActivity.class);
                        intent5.putExtras(bundle);
                        mContext.startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(mContext, FoodsListActivity.class);
                        intent6.putExtras(bundle);
                        mContext.startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent(mContext, FoodsListActivity.class);
                        intent7.putExtras(bundle);
                        mContext.startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8 = new Intent(mContext, FoodsListActivity.class);
                        intent8.putExtras(bundle);
                        mContext.startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9 = new Intent(mContext, FoodsListActivity.class);
                        intent9.putExtras(bundle);
                        mContext.startActivity(intent9);
                        break;
                    case 10:
                        Intent intent10 = new Intent(mContext, FoodsListActivity.class);
                        intent10.putExtras(bundle);
                        mContext.startActivity(intent10);
                        break;
                    case 11:
                        Intent intent11 = new Intent(mContext, FoodsListActivity.class);
                        intent11.putExtras(bundle);
                        mContext.startActivity(intent11);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.imageview_widget);
            this.name = itemView.findViewById(R.id.name_widget);
        }
    }
}