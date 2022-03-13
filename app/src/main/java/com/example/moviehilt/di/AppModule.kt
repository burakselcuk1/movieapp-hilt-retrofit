package com.example.moviehilt.di

import android.content.Context
import androidx.room.Room
import com.example.moviehilt.Util.Cons.BASE_URL
import com.example.moviehilt.db.MovieDatabase
import com.example.moviehilt.service.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getRetrofitServiceInstance(retrofit: Retrofit): Api{
        return retrofit.create(Api::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideToDoDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, MovieDatabase::class.java,
        "movie_data"
    ).build()

    @Singleton
    @Provides
    fun provideToDoDao(
        db: MovieDatabase
    ) = db.toDoDao()

}