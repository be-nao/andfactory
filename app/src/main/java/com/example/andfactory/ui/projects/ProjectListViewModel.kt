package com.example.andfactory.ui.projects

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.example.andfactory.api.db.entity.Project
import com.example.andfactory.api.repository.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProjectListViewModel @Inject constructor(private val projectRepository: ProjectRepository) :
    ViewModel() {

    companion object {
        const val username = "google"
    }

    private val _projectList = MutableLiveData<Status<List<Project>>>()
    val projectList = _projectList.distinctUntilChanged()

    fun loadProjectList() {
        viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.IO) { projectRepository.getRepositoryList(username) }
            }
                .onSuccess { _projectList.value = Status.Success(it) }
                .onFailure { loadCached() }
        }
    }

    private fun loadCached() {
        viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.IO) { projectRepository.getCachedRepositoryList() }
            }
                .onSuccess { _projectList.value = Status.Success(it) }
                .onFailure { _projectList.value = Status.Failure(it) }
        }
    }

    @Suppress("DEPRECATION")
    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }
        return result
    }
}

sealed class Status<out T> {
    data class Success<T>(val data: T) : Status<T>()
    data class Failure(val throwable: Throwable) : Status<Nothing>()
}