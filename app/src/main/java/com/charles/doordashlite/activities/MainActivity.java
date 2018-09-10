package com.charles.doordashlite.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.charles.doordashlite.R;
import com.charles.doordashlite.fragments.DiscoverMainScreenFragment;
import com.charles.doordashlite.utils.FragmentManipulationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_activity_container)
    FrameLayout mMainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_main);
        ButterKnife.bind(this);
        setUpMainScreen();
    }

    private void setUpMainScreen() {
        Fragment fragment = new DiscoverMainScreenFragment();
        FragmentManipulationUtils.pushInitialScreen(
                getSupportFragmentManager(),
                R.id.main_activity_container,
                fragment,
                "discover_screen");
    }
}
