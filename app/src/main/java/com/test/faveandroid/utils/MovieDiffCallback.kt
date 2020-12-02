package com.test.faveandroid.utils

import androidx.recyclerview.widget.DiffUtil
import com.test.faveandroid.model.MovieResponse

class MovieDiffCallback : DiffUtil.ItemCallback<MovieResponse.Result>() {
    override fun areItemsTheSame(oldItem: MovieResponse.Result, newItem: MovieResponse.Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieResponse.Result, newItem: MovieResponse.Result): Boolean {
        return oldItem == newItem
    }
}