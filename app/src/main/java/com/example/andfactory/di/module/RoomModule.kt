package com.example.andfactory.di.module

import android.app.Application
import androidx.room.Room
import com.example.andfactory.api.db.ProjectDao
import com.example.andfactory.api.db.ProjectDatabase
import com.example.andfactory.api.github.GitHubService
import com.example.andfactory.api.repository.ProjectRepository
import com.example.andfactory.api.repository.ProjectRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = Room.databaseBuilder(
        application.applicationContext,
        ProjectDatabase::class.java,
        "com.example.andfactory.App"
    ).build()

    @Singleton
    @Provides
    fun provideProjectDao(projectDatabase: ProjectDatabase): ProjectDao =
        projectDatabase.projectDao()

    @Singleton
    @Provides
    fun provideProjectRepository(
        githubService: GitHubService,
        projectDao: ProjectDao
    ): ProjectRepository =
        ProjectRepositoryImpl(githubService, projectDao)
}