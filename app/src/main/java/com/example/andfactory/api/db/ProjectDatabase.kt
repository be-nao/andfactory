package com.example.andfactory.api.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.andfactory.api.db.entity.Project

@Database(entities = [Project::class], version = 1, exportSchema = false)
abstract class ProjectDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}