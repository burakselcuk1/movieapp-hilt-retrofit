package com.example.moviehilt.repository

import androidx.lifecycle.LiveData
import com.example.moviehilt.db.movieDao
import com.example.moviehilt.model.Result
import javax.inject.Inject


class SavedMovieRepository @Inject constructor(private val dao: movieDao) {

    suspend fun insertToDo(toDo: Result)=dao.insertMovie(toDo)
    suspend fun deleteToDo(toDo: Result)=dao.deleteTodo(toDo)
    val readAllData: LiveData<List<Result>> = dao.getAllToDoes()
}
