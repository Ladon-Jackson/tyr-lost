package com.example.tyrlost.models

import android.net.Uri
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun tierModelListToJson(value: List<TierModel>): String = Json.encodeToString(value)

    @TypeConverter
    fun tierModelListFromJson(value: String): List<TierModel> = Json.decodeFromString(value)

    @TypeConverter
    fun tierModelToJson(value: TierModel): String = Json.encodeToString(value)

    @TypeConverter
    fun tierModelFromJson(value: String): TierModel = Json.decodeFromString(value)



    //TODO fix these so TierModel can be removed from untiered field in TierListModel
    @TypeConverter
    fun uriListToJson(value: List<@Serializable(UriSerializer::class) Uri>): String = ""//Json.encodeToString(value)

    @TypeConverter
    fun uriListFromJson(value: String): List<Uri> = emptyList()//Json.decodeFromString(value)
}