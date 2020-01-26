package com.example.andfactory.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.andfactory.di.ViewModelKey
import com.example.andfactory.ui.projects.ProjectListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.gahfy.mvvm_base.di.ViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProjectListViewModel::class)
    abstract fun bindMainViewModel(viewModel: ProjectListViewModel): ViewModel

}
