package com.example.andfactory.api.github

import com.example.andfactory.api.github.response.Project
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    suspend fun getRepositoryList(@Path("user") user: String): Response<List<Project>>
}
