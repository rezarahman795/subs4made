package com.example.mainactivity.DB.movie;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Favourite.class},version = 1,exportSchema = false)
public abstract class FavouriteDB extends RoomDatabase {

    public abstract FavouriteDao favouriteDao();

}
