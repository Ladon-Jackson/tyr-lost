package com.example.tyrlost

import android.app.Application
import androidx.room.Room
import com.example.tyrlost.models.TierListDB
import com.example.tyrlost.models.TierListDao
import com.example.tyrlost.services.FileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Modules {

    @Provides
    @Singleton
    fun provideFileService(application: Application): FileService = FileService(
        context = application.applicationContext
    )

    @Provides
    @Singleton
    fun provideDB(application: Application): TierListDB = Room.databaseBuilder(
        application.applicationContext,
        TierListDB::class.java,
        "tierList.db"
    ).build()

    @Provides
    @Singleton
    fun provideDao(application: Application): TierListDao = provideDB(application).dao
}