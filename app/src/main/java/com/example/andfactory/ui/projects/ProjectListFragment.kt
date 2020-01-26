package com.example.andfactory.ui.projects

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andfactory.R
import com.example.andfactory.databinding.RepositoryListFragmentBinding
import com.example.andfactory.ui.readme.ReadMeFragment
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import net.gahfy.mvvm_base.di.ViewModelFactory
import javax.inject.Inject


class ProjectListFragment : DaggerFragment(), ProjectListController.RepoClickListener {

    companion object {
        fun newInstance() = ProjectListFragment()
    }

    private lateinit var viewModel: ProjectListViewModel
    private lateinit var binding: RepositoryListFragmentBinding
    private lateinit var controller: ProjectListController

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.repository_list_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ProjectListViewModel::class.java)
        setBinding()
        observeViewModel()
        viewModel.loadProjectList()
    }

    private fun setBinding() {
        controller = ProjectListController { item -> onClickRepo(item.toString()) }
        val linearLayoutManager = LinearLayoutManager(context)
        binding.apply {
            repositoryListRecyclerView.apply {
                adapter = controller.adapter
                layoutManager = linearLayoutManager
            }
        }
    }

    private fun observeViewModel() {
        viewModel.projectList.observe(viewLifecycleOwner, Observer {
            controller.projects = it
        })
    }

    override fun onClickRepo(url: String) {

        if (isInternetAvailable()) {
            fragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(
                R.id.container,
                ReadMeFragment.newInstance("https://github.com/google/0x0g-2018-badge/blob/master/README.md")
            )?.commit()
        } else {
            Snackbar.make(binding.root, "接続されてないよ", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
