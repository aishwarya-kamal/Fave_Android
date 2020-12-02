package com.test.fave.di

import com.test.faveandroid.bookMovie.BookMovieFragment
import com.test.faveandroid.di.bookMovie.BookMovieViewModelModule
import com.test.faveandroid.di.movie.MovieViewModelModule
import com.test.faveandroid.di.movieDetails.MovieDetailsViewModelModule
import com.test.faveandroid.movie.MovieFragment
import com.test.faveandroid.movieDetails.MovieDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [MovieViewModelModule::class])
    abstract fun contributeMovieFragment() : MovieFragment

    @ContributesAndroidInjector(modules = [MovieDetailsViewModelModule::class])
    abstract fun contributeMovieDetailsFragment() : MovieDetailsFragment

    @ContributesAndroidInjector(modules = [BookMovieViewModelModule::class])
    abstract fun contributeBookMovieFragment() : BookMovieFragment

}