package com.example.moviehilt.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviehilt.model.Result
import com.example.moviehilt.repository.movieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: movieRepository):ViewModel() {

    val _movieDetail = MutableLiveData<Result>()


    fun getMovieDetail(id:String)=viewModelScope.launch {
            repository.getMovieDetail(id).let {response ->
                if (response.isSuccessful){
                    _movieDetail.postValue(response.body())
                }
            }
    }
}