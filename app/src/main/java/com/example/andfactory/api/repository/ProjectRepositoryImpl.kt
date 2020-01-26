package com.example.andfactory.api.repository

import com.example.andfactory.api.db.ProjectDao
import com.example.andfactory.api.db.entity.Project
import com.example.andfactory.api.github.GitHubService
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepositoryImpl @Inject constructor(
    private val service: GitHubService,
    private val userDao: ProjectDao
) : ProjectRepository {

    override suspend fun getRepositoryList(username: String): List<Project>? {
        try {
            val projectList = service.getRepositoryList(username)
            projectList.forEach {
                userDao.upsertProject(it)
            }
        } catch (e: Exception) {
            e
        }
        return userDao.getAllProject()
    }
}
