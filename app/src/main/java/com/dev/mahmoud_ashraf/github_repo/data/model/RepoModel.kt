package com.dev.mahmoud_ashraf.github_repo.data.model

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */

data class RepoModel(
    val createdAt: String? = "",
    val description: String? = "",
    val forksCount: Int? = 0,
    val fullName: String? = "",
    val hasIssues: Boolean? = false,
    val hasProjects: Boolean? = false,
    val hasWiki: Boolean? = false,
    val name: String? = "",
    val url: String? = "",
    val watchersCount: Int? = 0
)