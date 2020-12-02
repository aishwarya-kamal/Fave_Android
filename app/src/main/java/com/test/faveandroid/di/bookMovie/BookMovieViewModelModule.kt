package com.test.faveandroid.di.bookMovie

import androidx.lifecycle.ViewModel
import com.test.faveandroid.bookMovie.BookMovieViewModel
import com.test.faveandroid.di.ViewModelKey
import com.test.faveandroid.movie.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class BookMovieViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindBookMovieViewModel(bookMovieViewModel: BookMovieViewModel): ViewModel
}