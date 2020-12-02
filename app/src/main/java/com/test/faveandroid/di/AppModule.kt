package com.test.faveandroid.di

import com.test.faveandroid.data.remote.MovieService
import com.test.faveandroid.repository.DefaultRepository
import com.test.faveandroid.utils.BASE_URL
import com.test.faveandroid.utils.ResponseHandler
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    fun provideMovieService(): MovieService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }

    @Singleton
    @Provides
    fun provideRepository(
        service: MovieService
    ): DefaultRepository {
        return DefaultRepository(
            movieService = service
        )
    }

}