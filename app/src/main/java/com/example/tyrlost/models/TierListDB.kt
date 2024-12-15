package com.example.tyrlost.models

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [TierListModel::class],
    version = 1
)

abstract class TierListDB: RoomDatabase() {

    abstract val dao: TierListDao
}