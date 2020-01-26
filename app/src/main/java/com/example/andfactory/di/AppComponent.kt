package com.example.andfactory.di

import com.example.andfactory.App
import com.example.andfactory.api.RetrofitModule
import com.example.andfactory.di.module.ActivityModule
import com.example.andfactory.di.module.FragmentModule
import com.example.andfactory.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        RetrofitModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    override fun inject(application: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}
