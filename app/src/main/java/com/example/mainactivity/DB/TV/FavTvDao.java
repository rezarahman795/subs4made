package com.example.mainactivity.DB.TV;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mainactivity.DB.movie.Favourite;

import java.util.List;
@Dao
public interface FavTvDao {

    @Insert
    public void addDataFav(FavTV favTV);

    @Query("SELECT * FROM favourite_tv")
    public List<FavTV> getDataFav();

    @Query("SELECT EXISTS (SELECT 1 FROM favourite_tv WHERE id=:id)")
    public int x_Favourite (int id);

    @Delete
    public void delete (FavTV favTV);
}
