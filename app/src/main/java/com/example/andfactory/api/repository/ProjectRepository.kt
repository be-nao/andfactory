package com.example.andfactory.api.repository

import com.example.andfactory.api.db.entity.Project


interface ProjectRepository {
    suspend fun getRepositoryList(username: String): List<Project>?
}