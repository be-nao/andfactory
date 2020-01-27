package com.example.andfactory.ui.projects

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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
            try {
                val response = projectRepository.getRepositoryList(username)
                projectList.postValue(response)
            } catch (e: Exception) {
                errorObserver.postValue(e)
            }
        }
    }

    @Suppress("DEPRECATION")
    fun isInternetAvailable(context:Context): Boolean {
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
