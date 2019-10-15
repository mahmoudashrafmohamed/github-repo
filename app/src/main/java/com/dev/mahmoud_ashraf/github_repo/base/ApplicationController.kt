package com.dev.mahmoud_ashraf.github_repo.base

import android.app.Application
import com.dev.mahmoud_ashraf.github_repo.BuildConfig
import com.dev.mahmoud_ashraf.github_repo.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector

import timber.log.Timber
import javax.inject.Inject

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */

class ApplicationController : Application(), HasAndroidInjector {

    @Inject lateinit var androidInjector : DispatchingAndroidInjector<Any>


    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }


    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}