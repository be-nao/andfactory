package com.example.andfactory.ui.projects

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andfactory.api.db.entity.Project
import com.example.andfactory.api.repository.ProjectRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProjectListViewModel @Inject constructor(private val projectRepository: ProjectRepository) :
    ViewModel() {

    companion object {
        const val username = "be-nao"
    }

    var projectList: MutableLiveData<List<Project>> = MutableLiveData()
    var errorObserver: MutableLiveData<Throwable> = MutableLiveData()

    fun loadProjectList() {
        viewModelScope.launch {
            val response = projectRepository.getRepositoryList(username)
            projectList.postValue(response)
        }

    }

}
