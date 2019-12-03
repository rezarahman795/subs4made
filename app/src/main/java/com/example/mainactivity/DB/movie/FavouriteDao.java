package com.example.mainactivity.DB.movie;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;


@Dao
public interface FavouriteDao {

    @Insert
    public void addDataFav(Favourite favourite);

    @Query("SELECT * FROM favourite")
    public List<Favourite> getDataFav();

    @Query("SELECT EXISTS (SELECT 1 FROM favourite WHERE id=:id)")
    public int x_Favourite (int id);

    @Delete
    public void delete (Favourite favourite);
}
