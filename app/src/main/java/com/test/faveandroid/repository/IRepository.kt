package com.test.faveandroid.repository

import com.test.faveandroid.model.MovieDetailsResponse
import com.test.faveandroid.model.MovieResponse
import io.reactivex.rxjava3.core.Flowable

interface IRepository {
    fun getMovies(): Flowable<MovieResponse>
    fun getMovieDetails(movieId: Int): Flowable<MovieDetailsResponse>
}