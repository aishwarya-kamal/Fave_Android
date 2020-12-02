package com.test.faveandroid.di.movie

import androidx.lifecycle.ViewModel
import com.test.faveandroid.di.ViewModelKey
import com.test.faveandroid.movie.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(movieViewModel: MovieViewModel): ViewModel
}