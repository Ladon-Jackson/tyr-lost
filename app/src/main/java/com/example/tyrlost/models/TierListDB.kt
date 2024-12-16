package com.example.tyrlost.models

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [TierListModel::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class TierListDB: RoomDatabase() {

    abstract val dao: TierListDao
}