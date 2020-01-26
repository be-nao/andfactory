package com.example.andfactory.ui.projects

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andfactory.api.github.GitHubService
import com.example.andfactory.api.github.response.Project
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProjectListViewModel @Inject constructor(private val gitHubService: GitHubService) :
    ViewModel() {

    companion object {
        const val username = "google"
    }

    var projectList: MutableLiveData<List<Project>> = MutableLiveData()
    var errorObserver: MutableLiveData<Throwable> = MutableLiveData()

    fun loadProjectList() {
        viewModelScope.launch {
            val response = gitHubService.getRepositoryList(username)
            try {
                if (response.isSuccessful) {
                    projectList.postValue(response.body())
                }
            } catch (t: Throwable) {
                errorObserver.postValue(t)
            }
        }

    }

}
