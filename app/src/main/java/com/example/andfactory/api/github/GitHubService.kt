package com.example.andfactory.api.github


import com.example.andfactory.api.db.entity.Project
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    suspend fun getRepositoryList(@Path("user") user: String): List<Project>
}
