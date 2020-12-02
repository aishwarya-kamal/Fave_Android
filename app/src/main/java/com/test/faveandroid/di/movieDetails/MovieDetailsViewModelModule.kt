package com.test.faveandroid.di.movieDetails

import androidx.lifecycle.ViewModel
import com.test.faveandroid.di.ViewModelKey
import com.test.faveandroid.movieDetails.MovieDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieDetailsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    abstract fun bindMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel
}