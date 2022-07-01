package com.example.m10119002sensormap;

import android.os.Bundle;

import com.example.m10119002sensormap.fragments.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends FragmentActivity {
    NavigationBarView bot_nav;
    private Fragment profileFragment;
    private Fragment aboutFragment;
    private Fragment locationsFragment;
    private static final int NUM_PAGES = 3;
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationBarView bot_nav = findViewById(R.id.bottom_navigation);
        profileFragment = new ProfileFragment();
        locationsFragment = new LocationsFragment();
        aboutFragment= new AboutFragment();

        viewPager = findViewById(R.id.pager2_main);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bot_nav.setSelectedItemId(R.id.ic_profile);
                        break;
                    case 1:
                        bot_nav.setSelectedItemId(R.id.ic_map);
                        break;
                    case 2:
                        bot_nav.setSelectedItemId(R.id.ic_about);
                        break;
                }
            }
        });
        bot_nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.ic_profile:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.ic_map:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.ic_about:
                    viewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });

        bot_nav.setSelectedItemId(R.id.ic_map);
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new ProfileFragment();
                case 1:
                    return new LocationsFragment();
                case 2:
                    return new AboutFragment();
            }
            return new LocationsFragment();
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

}
