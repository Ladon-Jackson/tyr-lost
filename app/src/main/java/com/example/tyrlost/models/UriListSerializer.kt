@file:OptIn(ExperimentalSerializationApi::class)

package com.example.tyrlost.models

import android.net.Uri
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.serializer

object UriListSerializer: KSerializer<List<Uri>> {

    private val delegateSerializer = serializer<List<String>>()

    override val descriptor = SerialDescriptor("UriList", delegateSerializer.descriptor)

    override fun serialize(encoder: Encoder, value: List<Uri>) {
        encoder.encodeSerializableValue(delegateSerializer, value.map{it.toString()})
    }

    override fun deserialize(decoder: Decoder): List<Uri> {
        val stringList = decoder.decodeSerializableValue(delegateSerializer)
        return stringList.map { Uri.parse(it) }
    }
}