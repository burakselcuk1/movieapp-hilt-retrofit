package com.example.moviehilt.service

import com.example.moviehilt.Util.Cons.API_KEY
import com.example.moviehilt.model.Result
import com.example.moviehilt.model.movie
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Single

interface Api {

    @GET("3/movie/popular?")
    suspend fun getMovieList(
        @Query("api_key")
        api_key:String=API_KEY
    ):Response<movie>

    @GET("3/movie/{movie_id}?")
    suspend fun getMovieDetail(
        @Path ("movie_id") id:String,
        @Query("api_key") api_key: String = API_KEY
    ):Response<Result>




}