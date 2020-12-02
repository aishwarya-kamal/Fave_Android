package com.test.faveandroid.movieDetails

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.test.fave.di.ViewModelProviderFactory
import com.test.faveandroid.R
import com.test.faveandroid.databinding.MovieDetailsFragmentBinding
import com.test.faveandroid.model.MovieDetailsResponse
import com.test.faveandroid.utils.Resource
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class MovieDetailsFragment : DaggerFragment(), View.OnClickListener {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var binding: MovieDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.movie_details_fragment,
            container,
            false
        )

        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this, factory).get(MovieDetailsViewModel::class.java)

        val args = MovieDetailsFragmentArgs.fromBundle(requireArguments())

        binding.movie = args.movie

        binding.movieDetailsBook.setOnClickListener(this)

        viewModel.getMovieDetails(args.movie.id)

        viewModel.movieDetailsResponseLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.movieDetails = it.data
                    showMovieDetails()
                }
                Resource.Status.ERROR -> showError(it.message!!)
                Resource.Status.LOADING -> showLoading()
            }
        })

        return binding.root
    }

    override fun onClick(p0: View?) {
        this.findNavController().navigate(
            MovieDetailsFragmentDirections
                .actionMovieDetailsFragmentToBookMovieFragment()
        )
    }

    private fun showMovieDetails() {

        // Dynamically creating chips for Genres
        var genres: List<MovieDetailsResponse.Genre>? = binding.movieDetails?.genres
        if (genres != null && genres.isNotEmpty()) {
            for (genre in genres) {
                createChips(genre.name)
            }
        } else {
            createChips("Genre unavailable")
        }
    }

    private fun createChips(genre: String) {
        val chip = Chip(context)
        chip.text = genre
        chip.setChipBackgroundColorResource(R.color.colorAccent)
        chip.textSize = 18F
        chip.setTextColor(Color.WHITE)
        binding.movieDetailsChipGroup.addView(chip)
    }

    private fun showError(message: String) {
        Timber.e("Error from Response $message")
        Toast.makeText(context, "Sorry no data available!", Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        Timber.d("Loading now")
    }

}
