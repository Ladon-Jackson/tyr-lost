//TODO DI
//package com.example.tyrlost.dependencyInjection
//
//import android.app.Application
//import com.example.tyrlost.repos.FileService
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//class Modules {
//
//    @Provides
//    @Singleton
//    fun provideFileService(application: Application): FileService =
//        FileService(context = application.applicationContext)
//}