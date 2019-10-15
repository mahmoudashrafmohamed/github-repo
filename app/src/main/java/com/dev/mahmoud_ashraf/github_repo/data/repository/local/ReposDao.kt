package com.dev.mahmoud_ashraf.github_repo.data.repository.local

import androidx.room.*
import com.dev.mahmoud_ashraf.github_repo.data.entity.ReposEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by dev.mahmoud_ashraf on 10/15/2019.
 */
@Dao
interface ReposDao {

    @get:Query("SELECT * FROM repos")
    val getReposDao: Single<List<ReposEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepoDao(entity: ReposEntity)

    @Delete
    fun deleteRepDao(entity: ReposEntity)

    @Query("DELETE FROM repos")
    fun deleteAll(): Int

}
