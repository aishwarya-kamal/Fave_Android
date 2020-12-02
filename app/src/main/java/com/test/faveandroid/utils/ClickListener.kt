package com.test.faveandroid.utils

import com.test.faveandroid.model.MovieResponse

class ClickListener(val clickListener: (movie: MovieResponse.Result) -> Unit) {
    fun onClick(movie: MovieResponse.Result) = clickListener(movie)
}