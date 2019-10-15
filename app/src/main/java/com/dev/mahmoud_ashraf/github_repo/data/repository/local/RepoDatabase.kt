package com.dev.mahmoud_ashraf.github_repo.data.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dev.mahmoud_ashraf.github_repo.data.entity.ReposEntity

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */

@Database(entities = [ReposEntity::class], version = 2, exportSchema = false)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun reposDao(): ReposDao

}