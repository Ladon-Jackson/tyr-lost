package com.example.tyrlost.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TierListDao {

    @Upsert
    suspend fun upsertTierList(tierList: TierListModel)

    @Delete
    suspend fun deleteTierList(tierList: TierListModel)

    @Query("SELECT * FROM tierLists")
    fun getTierLists(): Flow<List<TierListModel>>

}