package com.example.moviehilt.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviehilt.model.Result
import com.example.moviehilt.repository.SavedMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedFragmentViewModel @Inject constructor(private val repository: SavedMovieRepository): ViewModel(){

    val readAllData: LiveData<List<Result>> = repository.readAllData

    fun addMovie(movie: Result)=viewModelScope.launch {
        repository.insertToDo(movie)
    }

}