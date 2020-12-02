package com.test.faveandroid.di

import android.content.Context
import com.test.fave.di.FragmentBuilderModule
import com.test.faveandroid.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        FragmentBuilderModule::class,
        ViewModelFactoryModule::class,
        AppModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}