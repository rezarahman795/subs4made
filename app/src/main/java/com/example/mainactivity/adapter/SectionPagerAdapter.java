package com.example.mainactivity.adapter;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mainactivity.R;
import com.example.mainactivity.view.FavMovieFragment;
import com.example.mainactivity.view.FavTvFragment;
import com.example.mainactivity.view.FavTvFragment;
import com.example.mainactivity.view.MovieFragment;
import com.example.mainactivity.view.SerialTVfragment;

public class SectionPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;

    public SectionPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.film,
            R.string.serial_tv,
            R.string.fav_movie,
            R.string.fav_tv
    };
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Activity activity =  null;
        switch (position) {
            case 0:
                fragment = new MovieFragment();
                break;
            case 1:
                fragment = new SerialTVfragment();
                break;
            case 2:
                fragment = new FavMovieFragment();
                break;
            case 3:
                fragment = new FavTvFragment();
                break;
        }
        return fragment;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
    @Override
    public int getCount() {
        return 4;
    }
}
