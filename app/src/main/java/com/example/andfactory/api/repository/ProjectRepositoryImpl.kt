package com.example.andfactory.api.repository

import com.example.andfactory.api.db.ProjectDao
import com.example.andfactory.api.db.entity.Project
import com.example.andfactory.api.github.GitHubService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepositoryImpl @Inject constructor(
    private val service: GitHubService,
    private val projectDao: ProjectDao
) : ProjectRepository {

    // TODO 本当はここでTryCatchしたい
    override suspend fun getRepositoryList(username: String): List<Project> {
        val projectList = service.getRepositoryList(username)
        projectList.forEach {
            projectDao.upsertProject(it)
        }
        return projectList
    }
}
