package com.charles.doordashlite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.charles.doordashlite.R;
import com.charles.doordashlite.adapters.RestaurantListViewAdapter;
import com.charles.doordashlite.models.Restaurant;
import com.charles.doordashlite.rest.GateWay;
import com.charles.doordashlite.rest.RestClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverMainScreenFragment extends Fragment {
    @BindView(R.id.restaurant_list_view)
    RecyclerView restaurantListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.restaurant_list_screen_fragment, container, false);
        ButterKnife.bind(this, view);
        setUpListView();
        return view;
    }

    private void setUpListView() {
        double lat = 37.422740;
        double lng = -122.139956;
        int offset = 0;
        int limit = 50;

        GateWay service = RestClient.getGateWay().create(GateWay.class);

        Call<List<Restaurant>> call = service.getRestaurant(lat, lng, offset, limit);
        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                List<Restaurant> mList = response.body();
                loadView(mList);
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void loadView(List<Restaurant> restaurantList) {
        List<Restaurant> list = restaurantList;
        restaurantListView.setLayoutManager(new LinearLayoutManager(getContext()));
        RestaurantListViewAdapter adapter = new RestaurantListViewAdapter(list, getContext());
        restaurantListView.setAdapter(adapter);
    }
}
