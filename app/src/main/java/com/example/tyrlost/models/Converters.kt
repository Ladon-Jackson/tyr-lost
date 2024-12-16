package com.example.tyrlost.models

import android.net.Uri
import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun tierModelListToJson(value: List<TierModel>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun tierModelListFromJson(value: String): List<TierModel> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun uriListToJson(value: List<Uri>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun uriListFromJson(value: String): List<Uri> {
        return Json.decodeFromString(value)
    }
}