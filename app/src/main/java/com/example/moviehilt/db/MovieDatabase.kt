package com.example.moviehilt.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviehilt.model.Result


@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun toDoDao():movieDao

}