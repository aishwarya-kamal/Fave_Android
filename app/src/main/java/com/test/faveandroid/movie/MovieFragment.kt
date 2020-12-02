package com.test.faveandroid.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.test.fave.di.ViewModelProviderFactory
import com.test.faveandroid.R
import com.test.faveandroid.databinding.MovieFragmentBinding
import com.test.faveandroid.model.MovieResponse
import com.test.faveandroid.utils.ClickListener
import com.test.faveandroid.utils.Resource
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class MovieFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var binding: MovieFragmentBinding
    private lateinit var viewModel: MovieViewModel

    private val adapter = MovieAdapter(ClickListener {
        this.findNavController().navigate(
            MovieFragmentDirections
                .actionMovieFragmentToMovieDetailsFragment(it)
        )
    })


    private fun showListOfMovies(movies: List<MovieResponse.Result>?) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        adapter.submitList(movies)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.movie_fragment,
            container,
            false
        )

        binding.lifecycleOwner = this

        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)

        viewModel.getMovies()

        viewModel.responseLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> showListOfMovies(it.data?.results)
                Resource.Status.ERROR -> showError(it.message!!)
                Resource.Status.LOADING -> showLoading()
            }
        })

        setUpSwipeToRefresh()

        return binding.root
    }

    private fun setUpSwipeToRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getMovies()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun showError(message: String) {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE

        Timber.e("Error from Response $message")
        Toast.makeText(context, "Sorry no data available!", Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        // Show Loading bar
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        Timber.d("Loading now")
    }

}