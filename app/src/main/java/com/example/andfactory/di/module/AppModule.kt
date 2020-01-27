package com.example.andfactory.di.module


import android.app.Application
import com.example.andfactory.App
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideBindApp(application: App): Application

}