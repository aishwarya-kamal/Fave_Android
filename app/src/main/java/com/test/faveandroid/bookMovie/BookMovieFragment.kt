package com.test.faveandroid.bookMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import com.test.faveandroid.R
import com.test.faveandroid.databinding.BookMovieFragmentBinding
import dagger.android.support.DaggerFragment

class BookMovieFragment : DaggerFragment() {

    val book_movie_url = "https://www.cathaycineplexes.com.sg"
    private lateinit var binding: BookMovieFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.book_movie_fragment,
            container,
            false
        )

        binding.lifecycleOwner = this

        webViewSetUp()

        return binding.root
    }

    private fun webViewSetUp() {
        val settings = binding.webView.settings
        settings.javaScriptEnabled = true
        settings.textZoom = 100
        settings.loadsImagesAutomatically = true
        binding.webView.fitsSystemWindows = true

        binding.webView.loadUrl(book_movie_url)

        binding.webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
                binding.webView.visibility = View.VISIBLE
            }
        }
    }
}