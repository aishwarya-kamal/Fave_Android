package com.test.faveandroid

import android.content.Context
import com.test.faveandroid.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class BaseApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }
}