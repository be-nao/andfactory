package com.example.andfactory.ui.projects

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

        viewModel.errorObserver.observe(viewLifecycleOwner, Observer {
            Snackbar.make(binding.root, "エラーが起きたよ", Snackbar.LENGTH_SHORT).show()
        })

    }

    override fun onClickRepo(url: String) {
        context?.let {
            if (viewModel.isInternetAvailable(it)) {
                fragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(
                    R.id.container,
                    ReadMeFragment.newInstance("https://github.com/google/0x0g-2018-badge")
                )?.commit()
            } else {
                Snackbar.make(
                    binding.root,
                    getString(R.string.not_connected_to_network),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}
