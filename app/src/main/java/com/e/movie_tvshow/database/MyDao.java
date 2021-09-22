package com.e.movie_tvshow.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void addMovie_favorite(MovieDB movieDB);

    @Query("select * from movie_detail")
    public List<MovieDB> getMovie_favorite();

    @Delete
    public void deleteMovie_favorite(MovieDB movieDB);

    @Update
    public void updateMovie_favorite(MovieDB movieDB);
}
