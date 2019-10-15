package com.dev.mahmoud_ashraf.github_repo.di

import android.app.Application
import com.dev.mahmoud_ashraf.github_repo.base.ApplicationController
import com.dev.mahmoud_ashraf.github_repo.di.components.DaggerAppComponent

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */
object AppInjector {
    fun init(gitApp : ApplicationController) {
        DaggerAppComponent.builder().application(gitApp as Application).build().inject(gitApp)
    }
}