package com.example.tyrlost

import android.app.Application
import com.example.tyrlost.services.FileService

//@Module
//@InstallIn(SingletonComponent::class)
class Modules {

//    @Provides
//    @Singleton
    fun provideFileService(application: Application): FileService =
        FileService(context = application.applicationContext)

//    @Provides
//    @Singleton
//    fun provideFileService(application: Application): FileService =
//        FileService(context = application.applicationContext)
}