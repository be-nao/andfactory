package com.example.andfactory.api.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.andfactory.api.db.entity.Project


@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProject(project: Project)

    @Query("SELECT * FROM projects")
    suspend fun getAllProject(): List<Project>
}