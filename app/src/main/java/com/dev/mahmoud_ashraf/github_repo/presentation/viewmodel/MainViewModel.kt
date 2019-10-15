package com.dev.mahmoud_ashraf.github_repo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.dev.mahmoud_ashraf.github_repo.domain.usecase.ReposUseCase
import javax.inject.Inject

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */


class MainViewModel
@Inject
constructor(private val useCase: ReposUseCase)
    : ViewModel() {

    var reposLiveData = useCase.reposLiveData()
    var isFirstLoad = true

    fun fetchRepos(pageNumber: Int) = useCase.getRepos(pageNumber)
    fun fetchCachedRepos() = useCase.getCachedRepos()
   private fun cleanObservables() = useCase.clear()

    override fun onCleared() {
        super.onCleared()
        cleanObservables()
    }



}