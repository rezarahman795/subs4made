package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mainactivity.DB.TV.FavTvDB;
import com.example.mainactivity.DB.movie.FavouriteDB;
import com.example.mainactivity.adapter.SectionPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    public static FavouriteDB favouriteDB;
    public static FavTvDB favTvDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionPagerAdapter sectionsPagerAdapter = new SectionPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager_main);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.main_tab_layout);
        tabs.setupWithViewPager(viewPager);

        favouriteDB = Room.databaseBuilder(getApplicationContext(),
                FavouriteDB.class,"fav_movie").allowMainThreadQueries().build();

        favTvDB = Room.databaseBuilder(getApplicationContext(),
                FavTvDB.class,"fav_tv").allowMainThreadQueries().build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings){
            Intent local_intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(local_intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
