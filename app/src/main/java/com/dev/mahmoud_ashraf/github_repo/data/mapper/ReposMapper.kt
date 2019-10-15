package com.dev.mahmoud_ashraf.github_repo.data.mapper

import com.dev.mahmoud_ashraf.github_repo.data.entity.ReposEntity
import com.dev.mahmoud_ashraf.github_repo.data.model.RepoModel
import com.dev.mahmoud_ashraf.github_repo.data.response.ReposResponse
import javax.inject.Inject

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */
 fun fromRemote(from: ReposResponse) = RepoModel(
        from.createdAt,
        from.description,
        from.forksCount,
        from.fullName,
        from.hasIssues,
        from.hasProjects,
        from.hasWiki,
        from.name,
        from.url,
        from.watchersCount

    )

fun fromDb(from: ReposEntity) = RepoModel(
    from.createdAt,
    from.description,
    from.forksCount,
    from.fullName,
    from.hasIssues,
    from.hasProjects,
    from.hasWiki,
    from.name,
    from.url,
    from.watchersCount

)

fun fromRemoteToLocal(from: ReposResponse) = ReposEntity(
    from.id,
    from.createdAt,
    from.description,
    from.forksCount,
    from.fullName,
    from.hasIssues,
    from.hasProjects,
    from.hasWiki,
    from.name,
    from.url,
    from.watchersCount

)