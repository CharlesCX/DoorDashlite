package com.charles.doordashlite.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.charles.doordashlite.R;
import com.charles.doordashlite.models.Restaurant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantListViewAdapter extends RecyclerView.Adapter<RestaurantListViewAdapter.RestaurantListItemViewHolder> {

    private List<Restaurant> restaurantList;
    private Context context;

    public RestaurantListViewAdapter(List<Restaurant> restaurantList, Context context) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @Override
    public RestaurantListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new RestaurantListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantListViewAdapter.RestaurantListItemViewHolder holder, int position) {
        final Restaurant restaurant = restaurantList.get(position);
        Glide
                .with(context)
                .load(restaurant.getCoverImgUrl())
                .into(holder.coverImage);

        holder.restaurantName.setText(restaurantList.get(position).getName());
        holder.restaurantDescription.setText(restaurantList.get(position).getDescription());
        if(restaurantList.get(position).getStatusType().equals("open")) {
            holder.restaurantStatus.setText(restaurantList.get(position).getStatus());
        } else {
            holder.restaurantStatus.setText("closed");
        }

    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class RestaurantListItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cover_image)
        ImageView coverImage;
        @BindView(R.id.restaurant_name)
        TextView restaurantName;
        @BindView(R.id.restaurant_description)
        TextView restaurantDescription;
        @BindView(R.id.restaurant_status)
        TextView restaurantStatus;

        View itemView;

        public RestaurantListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
