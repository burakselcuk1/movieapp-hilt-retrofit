package com.example.moviehilt.repository

import com.example.moviehilt.service.Api
import javax.inject.Inject

class movieRepository @Inject constructor(private val movieApi:Api) {

    suspend fun getMovies()=movieApi.getMovieList()

    suspend fun getMovieDetail(id:String)=movieApi.getMovieDetail(id)
}