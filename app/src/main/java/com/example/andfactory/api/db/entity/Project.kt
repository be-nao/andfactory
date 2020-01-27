package com.example.andfactory.api.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val html_url: String,
    val default_branch: String
)