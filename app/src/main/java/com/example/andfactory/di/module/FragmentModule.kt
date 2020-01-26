package com.example.andfactory.di.module

import com.example.andfactory.ui.projects.ProjectListFragment
import com.example.andfactory.ui.readme.ReadMeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): ProjectListFragment

    @ContributesAndroidInjector
    abstract fun contributeReadMeFragment(): ReadMeFragment
}
