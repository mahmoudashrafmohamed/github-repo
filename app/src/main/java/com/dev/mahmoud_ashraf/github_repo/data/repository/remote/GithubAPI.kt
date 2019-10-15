package com.dev.mahmoud_ashraf.github_repo.data.repository.remote

import com.dev.mahmoud_ashraf.github_repo.data.response.ReposResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */
interface GithubAPI {

    @GET("/users/JakeWharton/repos")
    fun getRepos(@Query("page") pageNumber: Int,
                 @Query("per_page") per_page: Int): Single<List<ReposResponse>>



}