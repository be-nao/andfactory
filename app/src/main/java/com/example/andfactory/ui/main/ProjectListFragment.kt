package com.example.andfactory.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.andfactory.R
import com.example.andfactory.databinding.RepositoryListFragmentBinding
import dagger.android.support.DaggerFragment
import net.gahfy.mvvm_base.di.ViewModelFactory
import javax.inject.Inject

class ProjectListFragment : DaggerFragment() {

    companion object {
        fun newInstance() = ProjectListFragment()
    }

    private lateinit var viewModel: ProjectListViewModel
    private lateinit var binding: RepositoryListFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.repository_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(ProjectListViewModel::class.java)
        setBinding()
        observeViewModel()
        viewModel.loadProjectList()
    }

    private fun setBinding(){
        binding.apply{

        }
    }

    private fun observeViewModel(){
        viewModel.projectList.observe(viewLifecycleOwner, Observer {
            // TODO
        })
    }

}
