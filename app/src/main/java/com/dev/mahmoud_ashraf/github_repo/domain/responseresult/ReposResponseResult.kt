package com.dev.mahmoud_ashraf.github_repo.domain.responseresult

import com.dev.mahmoud_ashraf.github_repo.data.model.RepoModel
import com.dev.mahmoud_ashraf.github_repo.data.response.ReposResponse
import com.dev.mahmoud_ashraf.github_repo.domain.NetworkState

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */
data class ReposResponseResult(
    val networkState: NetworkState,
    val repos: List<RepoModel>? = null
)