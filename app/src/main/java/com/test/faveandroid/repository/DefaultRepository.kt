package com.test.faveandroid.repository

import com.test.faveandroid.data.remote.MovieService
import com.test.faveandroid.model.MovieDetailsResponse
import com.test.faveandroid.model.MovieResponse
import com.test.faveandroid.utils.API_KEY
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Singleton

@Singleton
class DefaultRepository(
    val movieService: MovieService,
): IRepository {

    override fun getMovies(): Flowable<MovieResponse> {
        return movieService.getMovies(API_KEY, "release_date.desc")
    }

    override fun getMovieDetails(movieId: Int): Flowable<MovieDetailsResponse> {
        return movieService.getMovieDetails(movieId, API_KEY)
    }

}