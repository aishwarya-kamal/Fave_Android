package com.test.faveandroid.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.faveandroid.model.MovieDetailsResponse
import com.test.faveandroid.repository.DefaultRepository
import com.test.faveandroid.utils.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val repository: DefaultRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val _movieDetailsResponseLiveData: MutableLiveData<Resource<MovieDetailsResponse>> =
        MutableLiveData()
    val movieDetailsResponseLiveData: LiveData<Resource<MovieDetailsResponse>>
        get() = _movieDetailsResponseLiveData

    fun getMovieDetails(movieId: Int) {
        disposables.add(repository.getMovieDetails(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _movieDetailsResponseLiveData.setValue(Resource.loading(null)) }
            .subscribe(
                { result -> _movieDetailsResponseLiveData.setValue(Resource.success(result)) }
            ) { throwable -> _movieDetailsResponseLiveData.setValue(Resource.error(throwable.message!!)) })
    }

    override fun onCleared() {
        disposables.clear()
    }

}