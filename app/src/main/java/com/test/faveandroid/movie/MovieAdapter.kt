package com.test.faveandroid.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.faveandroid.databinding.ListItemBinding
import com.test.faveandroid.model.MovieResponse
import com.test.faveandroid.utils.ClickListener
import com.test.faveandroid.utils.MovieDiffCallback

class MovieAdapter(private val clickListener: ClickListener) :
    ListAdapter<MovieResponse.Result, RecyclerView.ViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ClubsViewHolder(
            ListItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ClubsViewHolder).bind(clickListener, getItem(position))
    }

    class ClubsViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: ClickListener, item: MovieResponse.Result) {
            binding.apply {
                clickListener = listener
                movie = item
                executePendingBindings()
            }
        }
    }
}