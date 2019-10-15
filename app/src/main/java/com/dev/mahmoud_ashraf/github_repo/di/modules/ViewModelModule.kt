package com.dev.mahmoud_ashraf.github_repo.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.mahmoud_ashraf.github_repo.base.ViewModelFactory
import com.dev.mahmoud_ashraf.github_repo.di.ViewModelKey
import com.dev.mahmoud_ashraf.github_repo.presentation.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */
@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel



    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}