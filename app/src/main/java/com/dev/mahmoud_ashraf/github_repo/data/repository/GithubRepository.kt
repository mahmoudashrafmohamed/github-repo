package com.dev.mahmoud_ashraf.github_repo.data.repository

import com.dev.mahmoud_ashraf.github_repo.data.entity.ReposEntity
import com.dev.mahmoud_ashraf.github_repo.data.mapper.fromDb
import com.dev.mahmoud_ashraf.github_repo.data.mapper.fromRemote
import com.dev.mahmoud_ashraf.github_repo.data.mapper.fromRemoteToLocal
import com.dev.mahmoud_ashraf.github_repo.data.model.RepoModel
import com.dev.mahmoud_ashraf.github_repo.data.repository.local.RepoDatabase
import com.dev.mahmoud_ashraf.github_repo.data.repository.remote.GithubAPI
import com.dev.mahmoud_ashraf.github_repo.utils.Constants
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */
class GithubRepository
@Inject
constructor(private val local: RepoDatabase,
            private val remote: GithubAPI
) {

    fun getRepos(pageNumber: Int): Flowable<List<RepoModel>?> {
        val remoteRepos =
            remote.getRepos(pageNumber,Constants.limit)
                .map { it.map {reposResponse ->
                    if (pageNumber == 1)
                        local.reposDao().insertRepoDao(
                            fromRemoteToLocal(reposResponse)
                           )
                    Timber.e("added")
                    fromRemote(reposResponse)

                }}
        return remoteRepos.toFlowable()
    }

    fun getCachedRepos(): Flowable<List<RepoModel>?> {
        val localRepos =
            local.reposDao().getReposDao.map {
                it.map {repoEntity ->
                    fromDb(repoEntity)
                }
            }
        return localRepos.toFlowable()
    }







    @Suppress("unused")
    fun clearDatabase(): Single<Int> {
        return Observable.fromCallable { local.reposDao().deleteAll() }.firstOrError()
    }



}