package com.example.andfactory.di.module

import com.example.andfactory.ui.main.ProjectListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): ProjectListFragment
}
