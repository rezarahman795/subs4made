package com.example.mainactivity.DB.TV;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {FavTV.class},version = 1,exportSchema = false)
public abstract class FavTvDB extends RoomDatabase {
    public abstract FavTvDao favTvDao();
}
