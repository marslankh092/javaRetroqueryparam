package com.example.fragments.rooms;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fragments.server.Search;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MoviesDao {
    //movies db
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void inAllMovies(List<Search> searches);

    @Update
    public void updateMovie(Search movies);

    @Delete
    public void deleteMovie(Search movies);

    @Query("Select * from Search")
    public List<Search> getStoredSearches();


    @Query("Select * from Search where :title= title")
    Search getSearchedDetail(String title);
}