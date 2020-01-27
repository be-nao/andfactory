package com.example.andfactory.ui.projects

import com.airbnb.epoxy.EpoxyController
import com.example.andfactory.EmptyViewBindingModel_
import com.example.andfactory.RepositoryListItemBindingModel_
import com.example.andfactory.api.db.entity.Project

class ProjectListController(private val callback: RepoClickListener) :
    EpoxyController() {

    interface RepoClickListener {
        fun onClickRepo(project: Project)
    }

    var projects: List<Project> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {

        if (projects.isEmpty()) {
            EmptyViewBindingModel_()
                .id(modelCountBuiltSoFar)
                .addTo(this)
        }

        projects.forEach {
            RepositoryListItemBindingModel_()
                .id(modelCountBuiltSoFar)
                .onClickRepo { _, _, _, _ ->
                    callback.onClickRepo(it)
                }
                .project(it)
                .addTo(this)
        }
    }
}