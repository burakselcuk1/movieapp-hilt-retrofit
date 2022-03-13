package com.example.moviehilt.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviehilt.model.Result

@Dao
interface movieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Result)

    @Delete
    suspend fun deleteTodo(movie: Result)

    @Query("SELECT * FROM movie_data ORDER BY id ASC ")
    fun getAllToDoes(): LiveData<List<Result>>

}