package com.example.andfactory.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andfactory.api.github.GitHubService
import com.example.andfactory.api.github.response.Project
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProjectListViewModel @Inject constructor(private val gitHubService: GitHubService) : ViewModel() {


    var projectList: MutableLiveData<List<Project>> = MutableLiveData()

    fun loadProjectList(){
        viewModelScope.launch {
            gitHubService.getRepositoryList("be-nao").let{
                if(it.isSuccessful) {
                    projectList.postValue(it.body())
                } else {
                    // TODO get cached repository data
                }
            }
        }
    }

}
