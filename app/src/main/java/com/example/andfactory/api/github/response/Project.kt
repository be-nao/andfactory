package com.example.andfactory.api.github.response

import com.squareup.moshi.Json

data class Project(
    val owner: Owner,
    @Json(name = "name")
    val repoName: String,
    @Json(name = "html_url")
    val url: String,
    @Json(name = "stargazers_count")
    val star: String
)