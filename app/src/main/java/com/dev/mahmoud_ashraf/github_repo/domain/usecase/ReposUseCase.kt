package com.dev.mahmoud_ashraf.github_repo.domain.usecase

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.mahmoud_ashraf.github_repo.data.repository.GithubRepository
import com.dev.mahmoud_ashraf.github_repo.domain.NetworkState
import com.dev.mahmoud_ashraf.github_repo.domain.responseresult.ReposResponseResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */

class ReposUseCase
@Inject
constructor(private val repo: GithubRepository,private val compositeDisposable: CompositeDisposable) {

    private val repoResponseResult: MutableLiveData<ReposResponseResult> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getRepos(pageNumber: Int) {
        compositeDisposable.add(repo.getRepos(pageNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe
            {
                repoResponseResult.postValue(ReposResponseResult(NetworkState.LOADING))
            }
            .subscribe(
                { repoList ->
                    repoList?.let {
                        repoResponseResult.postValue(ReposResponseResult(NetworkState.LOADED, it))
                    } ?: run {
                        repoResponseResult.postValue(ReposResponseResult(NetworkState.error("data is finished!")))
                    }
                },
                { throwable ->
                    throwable.printStackTrace()
                    repoResponseResult.postValue(ReposResponseResult(NetworkState.error(
                        throwable.message ?: "Something Went Wrong!")))
                }
            )
        )
    }

    fun getCachedRepos() {
        compositeDisposable.add(repo.getCachedRepos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe
            {
                repoResponseResult.postValue(ReposResponseResult(NetworkState.LOADING))
            }
            .subscribe(
                { repoList ->
                    Timber.e(""+repoList.toString())
                    repoList?.let {
                        repoResponseResult.postValue(ReposResponseResult(NetworkState.LOADED, it))
                    } ?: run {
                        repoResponseResult.postValue(ReposResponseResult(NetworkState.error("data is finished!")))
                    }
                },
                { throwable ->
                    throwable.printStackTrace()
                    repoResponseResult.postValue(ReposResponseResult(NetworkState.error(
                        throwable.message ?: "Something Went Wrong!")))
                }
            )
        )
    }


    fun reposLiveData(): LiveData<ReposResponseResult> = repoResponseResult



    fun clear() {
        compositeDisposable.clear()
    }





}