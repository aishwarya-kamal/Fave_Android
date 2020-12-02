package com.test.faveandroid.data.remote

import com.test.faveandroid.model.MovieDetailsResponse
import com.test.faveandroid.model.MovieResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") api_key: String,
        @Query("sort_by") sort_by: String
    ): Flowable<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Flowable<MovieDetailsResponse>

}