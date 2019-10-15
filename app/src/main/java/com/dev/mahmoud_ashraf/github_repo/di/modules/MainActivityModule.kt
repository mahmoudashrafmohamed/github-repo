package com.dev.mahmoud_ashraf.github_repo.di.modules

import com.dev.mahmoud_ashraf.github_repo.presentation.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity
}