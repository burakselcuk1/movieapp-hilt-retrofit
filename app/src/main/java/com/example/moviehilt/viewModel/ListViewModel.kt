package com.example.moviehilt.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviehilt.model.movie
import com.example.moviehilt.repository.movieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: movieRepository):ViewModel() {

    val _movie = MutableLiveData<movie>()


    init {
        getMovies()
    }

     fun getMovies()=viewModelScope.launch {
    repository.getMovies().let {response ->
        if (response.isSuccessful){
            _movie.postValue(response.body())
        }else{
            Log.e("Brk:MainViewModel","error")
        }
    }

    }

}