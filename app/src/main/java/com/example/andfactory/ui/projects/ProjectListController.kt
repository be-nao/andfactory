package com.example.andfactory.ui.projects

import com.airbnb.epoxy.EpoxyController
import com.example.andfactory.EmptyViewBindingModel_
import com.example.andfactory.RepositoryListItemBindingModel_
import com.example.andfactory.api.github.response.Project

class ProjectListController(private val adapterOnClick: (Any) -> Unit) : EpoxyController() {

    interface RepoClickListener {
        fun onClickRepo(url: String)
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
                .readne(adapterOnClick)
                .project(it)
                .addTo(this)
        }
    }
}