package com.example.andfactory.api.repository

import com.example.andfactory.api.db.ProjectDao
import com.example.andfactory.api.db.entity.Project
import com.example.andfactory.api.github.GitHubService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepositoryImpl @Inject constructor(
    private val service: GitHubService,
    private val userDao: ProjectDao
) : ProjectRepository {

    override suspend fun getRepositoryList(username: String): List<Project>? {
        val projectList = service.getRepositoryList(username)
        projectList.forEach {
            userDao.upsertProject(it)
        }
        return userDao.getAllProject()
    }
}
