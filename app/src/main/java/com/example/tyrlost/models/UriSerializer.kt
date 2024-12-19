@file:OptIn(ExperimentalSerializationApi::class)

package com.example.tyrlost.models

import android.net.Uri
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.serializer


object UriSerializer: KSerializer<Uri> {

    private val delegateSerializer = serializer<String>()

    override val descriptor = PrimitiveSerialDescriptor("Uri", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Uri) =
        encoder.encodeSerializableValue(delegateSerializer, value.toString())

    override fun deserialize(decoder: Decoder): Uri =
        Uri.parse(decoder.decodeSerializableValue(delegateSerializer))
}

object ColorSerializer: KSerializer<Color> {

    private val delegateSerializer = serializer<String>()

    override val descriptor = PrimitiveSerialDescriptor("Color", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Color) =
        encoder.encodeSerializableValue(delegateSerializer, value.value.toString())

    override fun deserialize(decoder: Decoder): Color =
        Color(decoder.decodeSerializableValue(delegateSerializer).toULong())
}