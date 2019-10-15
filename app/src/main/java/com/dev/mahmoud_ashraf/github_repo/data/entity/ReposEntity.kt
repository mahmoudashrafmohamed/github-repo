package com.dev.mahmoud_ashraf.github_repo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */

@Entity(tableName = "repos")
data class ReposEntity(

    @PrimaryKey
    @field:SerializedName("id")
    var id: Int? = null,
    var createdAt: String? = "",
    var description: String? = "",
    var forksCount: Int? = 0,
    var fullName: String? = "",
    var hasIssues: Boolean? = false,
    var hasProjects: Boolean? = false,
    var hasWiki: Boolean? = false,
    var name: String? = "",
    var url: String? = "",
    var watchersCount: Int? = 0
)