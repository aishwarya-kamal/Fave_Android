package com.test.faveandroid.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.faveandroid.model.MovieResponse
import com.test.faveandroid.repository.DefaultRepository
import com.test.faveandroid.utils.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class MovieViewModel  @Inject constructor(private val repository: DefaultRepository): ViewModel() {

    private val disposables = CompositeDisposable()
    private val _responseLiveData: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val responseLiveData: LiveData<Resource<MovieResponse>>
        get() = _responseLiveData

    fun getMovies() {
        disposables.add(repository.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _responseLiveData.setValue(Resource.loading(null)) }
            .subscribe(
                { result -> _responseLiveData.setValue(Resource.success(result)) }
            ) { throwable -> _responseLiveData.setValue(Resource.error(throwable.message!!)) })
    }

    override fun onCleared() {
        disposables.clear()
    }

}